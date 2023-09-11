package com.rafarocket.fce.pom

import androidx.compose.ui.test.junit4.ComposeTestRule
import androidx.compose.ui.test.onAllNodesWithTag
import androidx.compose.ui.test.onNodeWithTag

class ListHiringModel {
    fun getGroupItem(composeTestRule: ComposeTestRule) =
        composeTestRule.onAllNodesWithTag("groupItem", useUnmergedTree = true)[0]

    fun getListItem(composeTestRule: ComposeTestRule) =
        composeTestRule.onAllNodesWithTag("listItem", useUnmergedTree = true)[0]

    fun getSubContainerList(composeTestRule: ComposeTestRule) =
        composeTestRule.onNodeWithTag("subContainerList", useUnmergedTree = true)

    fun getContainerList(composeTestRule: ComposeTestRule) =
        composeTestRule.onNodeWithTag("containerList", useUnmergedTree = true)
}