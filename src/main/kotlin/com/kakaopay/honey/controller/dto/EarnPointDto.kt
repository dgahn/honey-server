package com.kakaopay.honey.controller.dto

data class EarnPointRequestDto(
    val category: String,
    val membershipCode: String,
    val point: Int,
    val partnerName: String
)

data class EarnPointResponseDto(
    val pointId: Long
)
