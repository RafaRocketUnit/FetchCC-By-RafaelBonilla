package com.rafarocket.fce.data

import arrow.core.left
import arrow.core.right
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.test.runTest
import org.junit.Test
import java.io.IOException


internal class HiringRepositoryRetrofitImplTest {

    private val hiringServiceApi: HiringServiceApi = mockk(relaxed = true)
    private val repositoryRetrofitImpl = HiringRepositoryRetrofitImpl(hiringServiceApi)
    private val error = ApiErrorDto("500 – Internal Server Error")

    @Test
    fun `When endpoint is correct`() = runTest {
        coEvery {
            hiringServiceApi.getHiring()
        } returns listOf(
            HiringDto(1,1,"a"),
            HiringDto(2,1,"a"),
            HiringDto(3,1,"a")
        )

        val expectedValue =  listOf(
            HiringDto(1,1,"a"),
            HiringDto(2,1,"a"),
            HiringDto(3,1,"a")
        ).right()

        val result = repositoryRetrofitImpl.getHiringList()

        coVerify { hiringServiceApi.getHiring() }

        assertEquals(expectedValue, result)
    }

    @Test
    fun `When endpoint is thrown an error`()  {
        val expectedValue =  ApiErrorDto("500 – Internal Server Error").left()
        val result = error.left()

        assertEquals(expectedValue, result)
    }

}