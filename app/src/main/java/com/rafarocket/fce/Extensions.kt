package com.rafarocket.fce

import com.rafarocket.fce.data.HiringDto
import com.rafarocket.fce.domain.Hiring
import com.rafarocket.fce.presentation.HiringUi

fun HiringDto.toHiring(): Hiring = Hiring(id, listId, name)

fun Hiring.toHiringUi(): HiringUi = HiringUi(id, listId, name)