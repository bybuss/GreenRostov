package bob.colbaskin.greenrostov.ui.screens.auth

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import bob.colbaskin.greenrostov.domain.local.DataStoreRepository
import bob.colbaskin.greenrostov.domain.network.AuthRepository
import bob.colbaskin.greenrostov.domain.network.restApi.RestApiRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * @author bybuss
 */
sealed class AuthState {
    object Authenticated : AuthState()
    object Unauthenticated : AuthState()
    object Loading : AuthState()
    data class Error(val message: String) : AuthState()
}

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val authRepository: AuthRepository,
    private val dataStoreRepository: DataStoreRepository,
    private val restApiRepository: RestApiRepository
) : ViewModel() {

    private val _authState = MutableStateFlow<AuthState>(AuthState.Unauthenticated)
    val authState: StateFlow<AuthState> = _authState.asStateFlow()

    init {
        checkAuthState()
    }

    fun checkAuthState() {
        if (authRepository.isLoggedIn()) {
            _authState.value = AuthState.Authenticated
        } else {
            _authState.value = AuthState.Unauthenticated
        }
    }

    fun login(email: String, password: String) {
        viewModelScope.launch {
            _authState.value = AuthState.Loading
            val loginResult = authRepository.login(email, password)

            if (loginResult) {
                val token = authRepository.getToken()

                if (token != null) {
                    dataStoreRepository.saveToken(token)
                    Log.d("AuthViewModel", "Token saved: $token")
                    _authState.value = AuthState.Authenticated
                } else {
                    _authState.value = AuthState.Error("Ошибка получения токена. Попробуйте снова.")
                }
            } else {
                _authState.value = AuthState.Error("Ошибка входа. Проверьте данные.")
            }
        }
    }


    fun signUp(
        firstName: String,
        lastName: String,
        address: String,
        email: String,
        password: String
    ) {
        viewModelScope.launch {
            _authState.value = AuthState.Loading
            val firebaseResult = authRepository.register(email, password)

            if (firebaseResult) {
                val token = authRepository.getToken()

                if (token != null) {
                    dataStoreRepository.saveToken(token)
                    Log.d("AuthViewModel", "Firebase token saved: $token")

                    try {
                        restApiRepository.signUp(firstName, lastName, address)
                        _authState.value = AuthState.Authenticated
                        Log.d("AuthViewModel", "REST API sign-up successful with user token: $token")
                    } catch (e: Exception) {
                        Log.e("AuthViewModel", "REST API sign-up failed", e)
                        _authState.value = AuthState.Error("Ошибка REST API регистрации. Попробуйте снова.")
                    }
                } else {
                    _authState.value = AuthState.Error("Ошибка получения токена. Попробуйте снова.")
                }
            } else {
                _authState.value = AuthState.Error("Ошибка регистрации в Firebase. Попробуйте снова.")
            }
        }
    }

    fun signOut() {
        viewModelScope.launch {
            authRepository.logout()
            _authState.value = AuthState.Unauthenticated
            Log.d("AuthViewModel", "User signed out")
        }
    }
}
