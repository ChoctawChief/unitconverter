package com.example.unitconverter

import androidx.compose.ui.test.assertTextContains
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@HiltAndroidTest
class ConverterViewModelInjectionTest {

    @get:Rule(order = 0)
    val hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @Before
    fun init() {
        hiltRule.inject()
    }

    @Test
    fun viewModelInjectionAndConversionFlow() {
        composeTestRule.onNodeWithTag("InputField").performTextInput("10")

        composeTestRule.onNodeWithTag("FromUnitDropdown").performClick()
        composeTestRule.onNodeWithTag("FromUnitDropdown-Meters").performClick()

        composeTestRule.onNodeWithTag("ToUnitDropdown").performClick()
        composeTestRule.onNodeWithTag("ToUnitDropdown-Feet").performClick()

        composeTestRule.onNodeWithTag("ConvertButton").performClick()

        composeTestRule.onNodeWithTag("ResultText")
            .assertExists()
            .assertTextContains("Result:")
    }
}
