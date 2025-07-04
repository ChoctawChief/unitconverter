// File: ConverterScreenTest.kt

package com.example.unitconverter

import androidx.compose.ui.semantics.SemanticsProperties
import androidx.compose.ui.semantics.getOrNull
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.ComposeTestRule
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import androidx.test.ext.junit.runners.AndroidJUnit4

@HiltAndroidTest
@RunWith(AndroidJUnit4::class)
class ConverterScreenTest {

    @get:Rule(order = 0)
    val hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @Before
    fun setUp() {
        hiltRule.inject()
    }

    @Test
    fun convertFeetToMeters() {
        composeTestRule.onNodeWithTag("InputField").performTextInput("100")


        composeTestRule.onNodeWithTag("FromUnitDropdown").performClick()
        composeTestRule.waitUntilExists(hasTestTag("FromUnitDropdown-Feet"))
        composeTestRule.onNodeWithTag("FromUnitDropdown-Feet").performClick()

        composeTestRule.onNodeWithTag("ToUnitDropdown").performClick()
        composeTestRule.waitUntilExists(hasTestTag("ToUnitDropdown-Meters"))
        composeTestRule.onNodeWithTag("ToUnitDropdown-Meters").performClick()

        composeTestRule.onNodeWithTag("ConvertButton").performClick()

        composeTestRule.waitUntil(timeoutMillis = 5_000) {
            composeTestRule
                .onNodeWithTag("ResultText")
                .fetchSemanticsNode()
                .config.getOrNull(SemanticsProperties.Text)
                ?.firstOrNull()
                ?.text
                ?.contains("Result: 30.48") == true
        }

        composeTestRule.onNodeWithTag("ResultText")
            .assertTextContains("Result: 30.48")
    }
}

fun ComposeTestRule.waitUntilExists(
    matcher: SemanticsMatcher,
    timeoutMillis: Long = 5_000
) {
    waitUntil(timeoutMillis) {
        onAllNodes(matcher).fetchSemanticsNodes().isNotEmpty()
    }
}
