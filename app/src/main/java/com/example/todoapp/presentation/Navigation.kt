package com.example.todoapp.presentation

import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color.Companion.Gray
import androidx.compose.ui.graphics.Color.Companion.Green
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavType
import androidx.navigation.compose.*
import com.example.todoapp.presentation.detail.DetailScreen
import com.example.todoapp.presentation.home.HomeScreen
import kotlin.reflect.typeOf

@ExperimentalMaterialApi
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
            composable(
                route = "/detail/{id}",
                arguments = listOf(
                    navArgument(name = "id") {
                        type = NavType.StringType
                        nullable = false
                    }
                )
            ) {
                DetailScreen()
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