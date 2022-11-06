package com.kakaopay.honey.controller.dto

import com.kakaopay.honey.domain.Category
import com.kakaopay.honey.domain.PointEventType
import com.kakaopay.honey.domain.PointHistory
import com.kakaopay.honey.util.toDateTimeString
import javax.validation.constraints.Size

data class SearchPointHistoriesResponseDto(
    val histories: List<SearchPointHistoryResponseDto>
)

data class SearchPointHistoryResponseDto(
    val approvedAt: String,
    val type: PointEventType,
    val category: Category,
    @field:Size(min = 10, max = 10)
    val membershipCode: String,
    val partnerName: String
)

fun List<PointHistory>.toSearchPointHistoriesResponseDto(): SearchPointHistoriesResponseDto {
    return SearchPointHistoriesResponseDto(this.map { it.toSearchPointHistoriesResponseDto() })
}

fun PointHistory.toSearchPointHistoriesResponseDto(): SearchPointHistoryResponseDto {
    return SearchPointHistoryResponseDto(
        approvedAt = approvedAt!!.toDateTimeString(),
        type = type,
        category = category,
        membershipCode = membershipCode,
        partnerName = partnerName
    )
}
