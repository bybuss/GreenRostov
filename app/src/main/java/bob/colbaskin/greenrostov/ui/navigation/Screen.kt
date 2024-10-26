package bob.colbaskin.greenrostov.ui.navigation

/**
 * @author bybuss
 */
sealed class Screen(val route: String) {
    object Welcome : Screen(route = "welcome_screen")
    object Home : Screen(route = "home_screen")
    object Login : Screen(route = "login_screen")
    object SignUp : Screen(route = "signup_screen")
    object Profile : Screen(route = "profile_screen")
}