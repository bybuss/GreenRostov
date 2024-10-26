package bob.colbaskin.greenrostov.ui.screens.onBoarding

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import bob.colbaskin.greenrostov.data.local.dataStore.DataStoreRepository
import bob.colbaskin.greenrostov.domain.network.AuthRepository
import bob.colbaskin.greenrostov.ui.navigation.Screen
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * @author bybuss
 */
class SplashViewModel @Inject constructor(
    private val dataStoreRepository: DataStoreRepository,
    private val authRepository: AuthRepository
) : ViewModel() {

    val isLoading = mutableStateOf(true)
    val startDestination = mutableStateOf(Screen.Welcome.route)

    init {
        viewModelScope.launch {
            val onboardingCompleted = dataStoreRepository.readOnBoardingState().first()
            if (onboardingCompleted) {
                if (authRepository.isLoggedIn()) {
                    startDestination.value = Screen.Home.route
                } else {
                    startDestination.value = Screen.Login.route
                }
            } else {
                // Если гайд не завершен, устанавливаем WelcomeScreen
                startDestination.value = Screen.Welcome.route
            }
            isLoading.value = false
        }
    }
}