package com.kakaopay.honey.controller.dto

import javax.validation.constraints.Size

data class EarnPointRequestDto(
    @field:Size(min = 10, max = 10)
    val membershipCode: String,
    val point: Long,
    val partnerId: Long
)

data class EarnPointResponseDto(
    val pointId: Long
)
