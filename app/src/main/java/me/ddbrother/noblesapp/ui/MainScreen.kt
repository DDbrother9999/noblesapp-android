package me.ddbrother.noblesapp.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.CalendarToday
import androidx.compose.material.icons.filled.Link
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.RestaurantMenu
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Sports
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.launch
import me.ddbrother.noblesapp.ui.screens.AthleticsScreen
import me.ddbrother.noblesapp.ui.screens.DirectoryScreen
import me.ddbrother.noblesapp.ui.screens.HelpfulLinksScreen
import me.ddbrother.noblesapp.ui.screens.MenuScreen
import me.ddbrother.noblesapp.ui.screens.ProfileScreen
import me.ddbrother.noblesapp.ui.screens.ScheduleScreen
import me.ddbrother.noblesapp.ui.screens.SettingsScreen
import me.ddbrother.noblesapp.ui.screens.WhoHasLunchScreen

enum class AppDestination(
    val route: String,
    val title: String,
    val icon: ImageVector
) {
    Profile("profile", "Profile", Icons.Default.Person),
    Schedule("schedule", "Schedule", Icons.Default.CalendarToday),
    Menu("menu", "Menu", Icons.Default.RestaurantMenu),
    Athletics("athletics", "Athletics", Icons.Default.Sports),
    Directory("directory", "Directory", Icons.AutoMirrored.Filled.List),
    WhoHasLunch("who_has_lunch", "Who has lunch?", Icons.Default.RestaurantMenu),
    HelpfulLinks("helpful_links", "Helpful Links", Icons.Default.Link),
    Settings("settings", "Settings", Icons.Default.Settings)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen() {
    val navController = rememberNavController()
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet {
                AppDestination.entries.forEach { destination ->
                    NavigationDrawerItem(
                        icon = { Icon(destination.icon, contentDescription = null) },
                        label = { Text(destination.title) },
                        selected = false,
                        onClick = {
                            navController.navigate(destination.route)
                            scope.launch { drawerState.close() }
                        },
                        modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding)
                    )
                }
            }
        }
    ) {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text("Nobles App") },
                    navigationIcon = {
                        IconButton(onClick = { scope.launch { drawerState.open() } }) {
                            Icon(Icons.Default.Menu, contentDescription = "Menu")
                        }
                    }
                )
            }
        ) { innerPadding ->
            NavHost(
                navController = navController,
                startDestination = AppDestination.Profile.route,
                modifier = Modifier.padding(innerPadding)
            ) {
                composable(AppDestination.Profile.route) { ProfileScreen() }
                composable(AppDestination.Schedule.route) { ScheduleScreen() }
                composable(AppDestination.Menu.route) { MenuScreen() }
                composable(AppDestination.Athletics.route) { AthleticsScreen() }
                composable(AppDestination.Directory.route) { DirectoryScreen() }
                composable(AppDestination.WhoHasLunch.route) { WhoHasLunchScreen() }
                composable(AppDestination.HelpfulLinks.route) { HelpfulLinksScreen() }
                composable(AppDestination.Settings.route) { SettingsScreen() }
            }
        }
    }
}
