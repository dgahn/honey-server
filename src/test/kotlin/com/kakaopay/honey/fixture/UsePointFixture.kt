package com.kakaopay.honey.fixture

import com.kakaopay.honey.controller.dto.UsePointRequestDto
import com.kakaopay.honey.controller.dto.UsePointResponseDto

object UsePointFixture {
    fun getRequestDto(): UsePointRequestDto {
        return UsePointRequestDto(
            membershipCode = "1111111111",
            point = 100,
            partnerId = 1L
        )
    }

    fun getResponseDto(id: Long = 1L): UsePointResponseDto {
        return UsePointResponseDto(
            id
        )
    }
}
