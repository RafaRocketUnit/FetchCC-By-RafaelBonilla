package com.rafarocket.fce.data

import arrow.core.Either

/**
 * Repository to retrieve the data from endpoint.
 */
interface HiringRepository {
    suspend fun getHiringList(): Either<ApiErrorDto, List<HiringDto>>
}