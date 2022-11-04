package com.kakaopay.honey.fixture

import com.kakaopay.honey.controller.dto.EarnPointRequestDto
import com.kakaopay.honey.controller.dto.EarnPointResponseDto

object EarnPointFixture {
    fun getRequestDto(): EarnPointRequestDto {
        return EarnPointRequestDto(
            category = "A",
            membershipCode = "1111111111",
            point = 100,
            partnerName = "상점_A"
        )
    }

    fun getResponseDto(): EarnPointResponseDto {
        return EarnPointResponseDto(
            1L
        )
    }
}
