package com.rafarocket.fce.presentation

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.test.core.app.ActivityScenario
import com.rafarocket.fce.poc.ListHiringController
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class HiringListScreenTest {
    @get: Rule
    val composeTestRule = createComposeRule()

    private val controller = ListHiringController()

    @Before
    fun setup() {
        ActivityScenario.launch(MainActivity::class.java)
    }

    @Test
    fun checkListItem() {
        controller.checkListItem(composeTestRule)
    }

    @Test
    fun checkGroupItem() {
        controller.checkGroupItem(composeTestRule)
    }

    @Test
    fun checkSubContainerList() {
        controller.checkSubContainerList(composeTestRule)
    }

    @Test
    fun checkContainerList() {
        controller.checkContainerList(composeTestRule)
    }
}