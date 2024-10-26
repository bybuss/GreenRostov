package bob.colbaskin.greenrostov.ui.navigation.main

import  androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import bob.colbaskin.greenrostov.ui.navigation.Screen
import bob.colbaskin.greenrostov.ui.screens.EventsScreen.EventsScreen
import bob.colbaskin.greenrostov.ui.screens.auth.AuthViewModel
import bob.colbaskin.greenrostov.ui.screens.auth.LoginScreen
import bob.colbaskin.greenrostov.ui.screens.auth.SignUpScreen
import bob.colbaskin.greenrostov.ui.screens.home.HomeScreen
import bob.colbaskin.greenrostov.ui.screens.onBoarding.WelcomeScreen
import bob.colbaskin.greenrostov.ui.screens.profile.ProfileScreen
import com.google.accompanist.pager.ExperimentalPagerApi


/**
 * @author bybuss
 */

@ExperimentalAnimationApi
@ExperimentalPagerApi
@Composable
fun AppNavigation(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    startDestination: String
) {
    val authViewModel: AuthViewModel = hiltViewModel()
    val bottomNavRoutes = bottomNavItems.map { it.route }

    Scaffold(
        bottomBar = {
            BottomNavBar(navController = navController, items = bottomNavItems)
        }
    ) { paddingValues ->
        NavHost(
            navController = navController,
            startDestination = startDestination,
            modifier = Modifier.padding(paddingValues)
        ) {
            composable(Screen.Welcome.route) {
                WelcomeScreen(navController = navController)
            }
            composable(Screen.Login.route) {
                LoginScreen(navController = navController, authViewModel = authViewModel)
            }
            composable(Screen.SignUp.route) {
                SignUpScreen(navController = navController, authViewModel = authViewModel)
            }
            composable(Screen.Home.route) {
                HomeScreen(modifier, authViewModel)
            }
            composable(Screen.Profile.route) {
                ProfileScreen(modifier)
            }
            composable(Screen.Events.route) {
                EventsScreen()
            }
        }
    }
}