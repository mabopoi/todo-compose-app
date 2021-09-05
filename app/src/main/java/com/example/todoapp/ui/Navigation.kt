package com.example.todoapp.ui

import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
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
import com.example.todoapp.ui.presentation.HomeScreen

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
    var selected = backStackEntry?.destination?.route == "/"
    BottomNavigation {
        BottomNavigationItem(
            selected = selected,
            onClick = { navigateHome },
            selectedContentColor = Green,
            unselectedContentColor = Gray,
            icon = { Icons.Default.Home }
        )
    }
}