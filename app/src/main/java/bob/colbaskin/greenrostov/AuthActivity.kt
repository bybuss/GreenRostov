package bob.colbaskin.greenrostov

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController
import bob.colbaskin.greenrostov.ui.navigation.auth.AuthNavigation
import bob.colbaskin.greenrostov.ui.screens.auth.AuthState
import bob.colbaskin.greenrostov.ui.screens.auth.AuthViewModel
import bob.colbaskin.greenrostov.ui.theme.GreenRostovTheme
import com.google.accompanist.pager.ExperimentalPagerApi
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AuthActivity : ComponentActivity() {

    @OptIn(ExperimentalAnimationApi::class, ExperimentalPagerApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GreenRostovTheme {
                val navController = rememberNavController()
                val authViewModel: AuthViewModel = hiltViewModel()

                AuthNavigation(
                    navController = navController,
                    authViewModel = authViewModel
                )

                val authState by authViewModel.authState.collectAsState()

                LaunchedEffect(authState) {
                    if (authState is AuthState.Authenticated) {
                        val intent = Intent(this@AuthActivity, MainActivity::class.java).apply {
                            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                        }
                        startActivity(intent)
                    }
                }
            }
        }
    }
}