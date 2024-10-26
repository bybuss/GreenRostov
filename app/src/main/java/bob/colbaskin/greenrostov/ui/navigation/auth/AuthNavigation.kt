package bob.colbaskin.greenrostov.ui.navigation.auth

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import bob.colbaskin.greenrostov.ui.screens.auth.LoginScreen
import bob.colbaskin.greenrostov.ui.screens.auth.SignUpScreen
import bob.colbaskin.greenrostov.ui.navigation.Screen
import bob.colbaskin.greenrostov.ui.screens.auth.AuthViewModel

/**
 * @author bybuss
 */

@Composable
fun AuthNavigation(
    navController: NavHostController,
    authViewModel: AuthViewModel
) {
    NavHost(
        navController = navController,
        startDestination = Screen.Login.route
    ) {
        composable(Screen.Login.route) {
            LoginScreen(navController = navController, authViewModel = authViewModel)
        }
        composable(Screen.SignUp.route) {
            SignUpScreen(navController = navController, authViewModel = authViewModel)
        }
    }
}