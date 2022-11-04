package com.kakaopay.honey.fixture

import com.kakaopay.honey.controller.dto.EarnPointRequestDto
import com.kakaopay.honey.controller.dto.EarnPointResponseDto

object EarnPointFixture {
    fun getRequestDto(): EarnPointRequestDto {
        return EarnPointRequestDto(
            membershipCode = "1111111111",
            point = 100,
            partnerId = 1L
        )
    }

    fun getResponseDto(): EarnPointResponseDto {
        return EarnPointResponseDto(
            1L
        )
    }
}
