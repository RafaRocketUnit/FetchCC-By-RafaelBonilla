package com.rafarocket.fce.data

import arrow.core.Either
import arrow.core.left
import arrow.core.right
import javax.inject.Inject

/**
 * Implementation of repository
 */
class HiringRepositoryRetrofitImpl @Inject constructor(private val hiringServiceApi: HiringServiceApi): HiringRepository {
    override suspend fun getHiringList(): Either<ApiErrorDto, List<HiringDto>> {
        return try {
            hiringServiceApi.getHiring().right()
        } catch (exception: Exception) {
            ApiErrorDto(exception.message.toString()).left()
        }
    }
}

