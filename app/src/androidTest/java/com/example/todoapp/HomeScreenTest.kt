package com.example.todoapp

import androidx.activity.viewModels
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.lifecycle.ViewModel
import com.example.todoapp.presentation.MainActivity
import com.example.todoapp.presentation.home.HomeScreen
import com.example.todoapp.presentation.home.HomeViewModel
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.HiltTestApplication
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@HiltAndroidTest
class HomeScreenTest {

    @get:Rule(order = 1)
    val hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 2)
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @ExperimentalMaterialApi
    @Before
    fun setup() {
        hiltRule.inject()
        composeTestRule.setContent {
            HomeScreen(
                navigateToDetailScreen = {},
                viewModel = composeTestRule.activity.viewModels<HomeViewModel>().value
            )
        }
    }

    @Test
    fun alertDialog_should_display() {
        composeTestRule.onNodeWithContentDescription("Add icon").performClick()
        composeTestRule.onNode(isDialog()).assertIsDisplayed()
    }
    
}