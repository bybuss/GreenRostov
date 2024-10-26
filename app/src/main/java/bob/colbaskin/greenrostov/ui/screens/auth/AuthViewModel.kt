package bob.colbaskin.greenrostov.ui.screens.auth

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import bob.colbaskin.greenrostov.domain.network.AuthRepository
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
    private val authRepository: AuthRepository
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
            val result = authRepository.login(email, password)
            val token = authRepository.getToken()
            if (result) {
                _authState.value = AuthState.Authenticated
                Log.d("AuthViewModel", "${token}")
            } else {
                _authState.value = AuthState.Error("Ошибка входа. Проверьте данные.")
            }
        }
    }

    fun signUp(email: String, password: String) {
        viewModelScope.launch {
            _authState.value = AuthState.Loading
            val result = authRepository.register(email, password)
            if (result) {
                _authState.value = AuthState.Authenticated
            } else {
                _authState.value = AuthState.Error("Ошибка регистрации. Попробуйте снова.")
            }
        }
    }

    fun signOut() {
        authRepository.logout()
        _authState.value = AuthState.Unauthenticated
    }
}
