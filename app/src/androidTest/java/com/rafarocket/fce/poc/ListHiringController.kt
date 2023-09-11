package com.rafarocket.fce.poc

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.ComposeTestRule
import androidx.compose.ui.test.onAllNodesWithTag
import com.rafarocket.fce.pom.ListHiringModel

class ListHiringController {

    private val listHiringModel = ListHiringModel()

    fun checkListItem(composeTestRule: ComposeTestRule) {
        uiIsVisible(composeTestRule)
        listHiringModel.getListItem(composeTestRule).assertIsDisplayed()
    }

    fun checkGroupItem(composeTestRule: ComposeTestRule) {
        uiIsVisible(composeTestRule)
        listHiringModel.apply {
            getGroupItem(composeTestRule).assertIsDisplayed()
        }
    }

    fun checkSubContainerList(composeTestRule: ComposeTestRule) {
        uiIsVisible(composeTestRule)
        listHiringModel.getSubContainerList(composeTestRule)
    }

    fun checkContainerList(composeTestRule: ComposeTestRule) {
        uiIsVisible(composeTestRule)
        listHiringModel.getContainerList(composeTestRule).assertIsDisplayed()
    }

    private fun uiIsVisible(composeTestRule: ComposeTestRule) {
        composeTestRule.waitUntil(timeoutMillis = 5000L) {
            composeTestRule
                .onAllNodesWithTag("containerList")
                .fetchSemanticsNodes().size == 1
        }
    }
}