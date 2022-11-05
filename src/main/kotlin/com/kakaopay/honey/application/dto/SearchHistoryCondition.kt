package com.kakaopay.honey.application.dto

import com.kakaopay.honey.util.toInstant
import org.springframework.data.domain.PageRequest
import java.time.Instant
import java.time.LocalDate

data class SearchHistoryCondition(
    val membershipCode: String,
    val startAt: LocalDate,
    val endAt: LocalDate,
    private val page: Int,
    private val size: Int
) {
    val pageRequest: PageRequest = PageRequest.of(page - 1, size)

    val startAtInstant: Instant
        get() = startAt.toInstant()

    val endAtInstant: Instant
        get() = endAt.toInstant()
}
