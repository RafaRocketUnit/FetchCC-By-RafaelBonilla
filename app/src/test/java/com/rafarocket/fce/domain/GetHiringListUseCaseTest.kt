package com.rafarocket.fce.domain

import arrow.core.Either
import arrow.core.left
import arrow.core.right
import com.rafarocket.fce.data.ApiErrorDto
import com.rafarocket.fce.data.HiringDto
import com.rafarocket.fce.data.HiringRepository
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.test.runTest
import org.junit.Test

internal class GetHiringListUseCaseTest {
    private val repository: HiringRepository = mockk(relaxed = true)
    private val useCase: GetHiringListUseCase = GetHiringListUseCase(repository)

    @Test
    fun `Invoke Use Case`() = runTest {

        coEvery {
            repository.getHiringList()
        } returns listOf(
            HiringDto(1,1,"a"),
            HiringDto(2,2,"a"),
            HiringDto(3,3,"a")
        ).right()

        val expectedValue = mapOf(
            Pair(1, listOf( Hiring(1,1,"a"))),
            Pair(2, listOf( Hiring(2,2,"a"))),
            Pair(3, listOf( Hiring(3,3,"a")))
        ).right()

        val result = useCase.invoke()

        coVerify { repository.getHiringList() }

        assertEquals(expectedValue, result)
    }

    @Test
    fun `Invoke Use Case on Exception`() = runTest {
        coEvery {
            repository.getHiringList()
        } returns ApiErrorDto("500 – Internal Server Error").left()

        val expectedValue = ApiError("500 – Internal Server Error").left()
        val result = useCase.invoke()

        coVerify { repository.getHiringList() }

        assertEquals(expectedValue, result)
    }
}