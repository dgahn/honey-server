package com.kakaopay.honey.controller.dto

import com.kakaopay.honey.domain.Category
import com.kakaopay.honey.domain.PointEventType
import com.kakaopay.honey.domain.PointHistory
import com.kakaopay.honey.util.toDateString

data class SearchPointHistoriesResponseDto(
    val histories: List<SearchPointHistoryResponseDto>
)

data class SearchPointHistoryResponseDto(
    val approvedAt: String,
    val type: PointEventType,
    val category: Category,
    val membershipCode: String,
    val partnerName: String
)

fun List<PointHistory>.toSearchPointHistoriesResponseDto(): SearchPointHistoriesResponseDto {
    return SearchPointHistoriesResponseDto(this.map { it.toSearchPointHistoriesResponseDto() })
}

fun PointHistory.toSearchPointHistoriesResponseDto(): SearchPointHistoryResponseDto {
    return SearchPointHistoryResponseDto(
        approvedAt = approvedAt!!.toDateString(),
        type = type,
        category = category,
        membershipCode = membershipCode,
        partnerName = partnerName
    )
}
