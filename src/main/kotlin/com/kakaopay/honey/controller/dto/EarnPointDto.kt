package com.kakaopay.honey.controller.dto

data class EarnPointRequestDto(
    val membershipCode: String,
    val point: Long,
    val partnerId: Long
)

data class EarnPointResponseDto(
    val pointId: Long
)
