package com.rafarocket.fce.domain

import androidx.core.text.isDigitsOnly
import arrow.core.Either
import arrow.core.left
import arrow.core.right
import com.rafarocket.fce.data.HiringDto
import com.rafarocket.fce.data.HiringRepository
import com.rafarocket.fce.toHiring
import javax.inject.Inject

/**
 * Use case class that manage the data retrieving.
 */
class GetHiringListUseCase @Inject constructor(private val repository: HiringRepository) {
    suspend operator fun invoke(): Either<ApiError, Map<Int, List<Hiring>>> = repository.getHiringList().fold(
            ifRight = {
                it.map(HiringDto::toHiring).mapOrderHiringList().right()
            },
            ifLeft = {
                ApiError(it.error).left()
            }
        )

    private fun List<Hiring>.mapOrderHiringList(): Map<Int, List<Hiring>> {
        return this.filterNot { it.name.isNullOrEmpty() }.sortedBy { it.listId }
            .groupBy { it.listId }.map { Pair(it.key, it.value.sortByName()) }.toMap()
    }

    private fun List<Hiring>.sortByName(): List<Hiring> {
        return this.sortedBy {
            if ((it.name?.split(" ")?.size ?: 0) > 1
                && it.name?.split(" ")?.get(1)?.isDigitsOnly() == true)
                it.name.split(" ")[1].toInt()
            else
                it.name?.length
        }
    }
}