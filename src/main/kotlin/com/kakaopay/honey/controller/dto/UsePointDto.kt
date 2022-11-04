package com.kakaopay.honey.controller.dto

data class UsePointRequestDto(
    val membershipCode: String,
    val point: Long,
    val partnerId: Long
)

data class UsePointResponseDto(
    val pointId: Long
)
