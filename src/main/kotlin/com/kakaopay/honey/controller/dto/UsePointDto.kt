package com.kakaopay.honey.controller.dto

import javax.validation.constraints.Size

data class UsePointRequestDto(
    @field:Size(min = 10, max = 10)
    val membershipCode: String,
    val point: Long,
    val partnerId: Long
)

data class UsePointResponseDto(
    val pointId: Long
)
