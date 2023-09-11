package com.rafarocket.fce.presentation

import app.cash.turbine.test
import arrow.core.left
import arrow.core.right
import com.rafarocket.fce.domain.ApiError
import com.rafarocket.fce.domain.GetHiringListUseCase
import com.rafarocket.fce.domain.Hiring
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test


@OptIn(ExperimentalCoroutinesApi::class)
internal class HiringViewModelTest {

    private val useCase: GetHiringListUseCase = mockk(relaxed = true)
    private val viewModel: HiringViewModel = HiringViewModel(useCase)

    @Before
    fun setup() {
        Dispatchers.setMain(UnconfinedTestDispatcher())
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `When the endpoint get the correct result`() = runTest {
        coEvery {
            useCase()
        } returns mapOf(
                Pair(1, listOf( Hiring(1,1,"a"))),
        Pair(2, listOf( Hiring(2,2,"a"))),
        Pair(3, listOf( Hiring(3,3,"a")))
        ).right()

        val expectedValue = ViewStates.HiringListObtained(mapOf(
            Pair(1, listOf( HiringUi(1,1,"a"))),
            Pair(2, listOf( HiringUi(2,2,"a"))),
            Pair(3, listOf( HiringUi(3,3,"a")))
        ))

        viewModel.fetchHiringList()
        coVerify { useCase() }

        viewModel.hiring.test {
            assertEquals(expectedValue, expectMostRecentItem())
        }
    }

    @Test
    fun `When the endpoint get the error`() = runTest {
        coEvery {
            useCase()
        } returns ApiError("500 Internal Server Error").left()

        val expectedValue = ViewStates.Error("500 Internal Server Error")
        viewModel.fetchHiringList()
        coVerify { useCase() }

        viewModel.hiring.test {
            assertEquals(expectedValue, expectMostRecentItem())
        }
    }

}