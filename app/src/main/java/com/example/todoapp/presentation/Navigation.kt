package com.example.todoapp.presentation

import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color.Companion.Gray
import androidx.compose.ui.graphics.Color.Companion.Green
import androidx.navigation.NavBackStackEntry
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.todoapp.presentation.home.HomeScreen

@Composable
fun Navigation() {
    val navController = rememberNavController()
    val backStack = navController.currentBackStackEntryAsState()
    Scaffold(bottomBar = {
        NavigationBar(
            backStackEntry = backStack.value,
            navigateHome = { navController.navigate(("/")) })
    }) {
        NavHost(navController = navController, "/") {
            composable("/") {
                HomeScreen { toDoItemId ->
                    navController.navigate("/detail/$toDoItemId")
                }
            }
        }
    }


}

@Composable
fun NavigationBar(
    backStackEntry: NavBackStackEntry?,
    navigateHome: () -> Unit
) {
    val selected = backStackEntry?.destination?.route == "/"
    BottomNavigation {
        BottomNavigationItem(
            selected = selected,
            onClick = { navigateHome() },
            selectedContentColor = Green,
            unselectedContentColor = Gray,
            icon = { Icon(imageVector = Icons.Default.Home, contentDescription = "Home icon") }
        )
    }
}