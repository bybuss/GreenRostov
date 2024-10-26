package bob.colbaskin.greenrostov.ui.navigation.main

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.ui.graphics.vector.ImageVector
import bob.colbaskin.greenrostov.ui.navigation.Screen

/**
 * @author bybuss
 */
data class NavItem(
    val icon: ImageVector,
    val route: String
)

val bottomNavItems = listOf(
    NavItem(Icons.Default.Home, Screen.Home.route),
    NavItem(Icons.Default.Person, Screen.Profile.route)
)
