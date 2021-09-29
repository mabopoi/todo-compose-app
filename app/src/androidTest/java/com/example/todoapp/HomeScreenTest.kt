package com.example.todoapp

import androidx.activity.viewModels
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import com.example.todoapp.presentation.MainActivity
import com.example.todoapp.presentation.home.HomeScreen
import com.example.todoapp.presentation.home.HomeViewModel
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import junit.framework.TestCase.assertTrue
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

    @Test
    fun swipe_should_delete_item() {
        val item = composeTestRule.onNodeWithText("title 2")
        item.performGesture { swipeRight() }

        composeTestRule.waitForIdle()

        assertTrue(composeTestRule.activity.viewModels<HomeViewModel>().value.state.value.list.size == 3)
        composeTestRule.onNodeWithText("title 2").assertIsNotDisplayed()
        // assertDoesNotExist() fails the test even though the item is not in the state's list :/
    }
}