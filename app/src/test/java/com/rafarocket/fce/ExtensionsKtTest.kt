package com.rafarocket.fce

import com.rafarocket.fce.data.HiringDto
import com.rafarocket.fce.domain.Hiring
import com.rafarocket.fce.presentation.HiringUi
import junit.framework.TestCase.assertEquals
import org.junit.Test


internal class ExtensionsKtTest {

    @Test
    fun `convert from HiringDto to Hiring`() {
        val hiringDto = HiringDto(1, 1, "item 43")
        val hiring = Hiring(1,1, "item 43")

        assertEquals(hiring, hiringDto.toHiring())
    }

    @Test
    fun `convert from Hiring to HiringUi`() {
        val hiring = Hiring(1, 1, "item 43")
        val hiringUi = HiringUi(1,1, "item 43")

        assertEquals(hiringUi, hiring.toHiringUi())
    }

}