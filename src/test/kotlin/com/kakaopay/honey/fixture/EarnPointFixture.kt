package com.kakaopay.honey.fixture

import com.kakaopay.honey.controller.dto.EarnPointRequestDto
import com.kakaopay.honey.controller.dto.EarnPointResponseDto

object EarnPointFixture {
    fun getRequestDto(
        membershipCode: String = "1111111111",
        point: Long = 100,
        partnerId: Long = 1L
    ): EarnPointRequestDto {
        return EarnPointRequestDto(
            membershipCode = membershipCode,
            point = point,
            partnerId = partnerId
        )
    }

    fun getResponseDto(id: Long = 1L): EarnPointResponseDto {
        return EarnPointResponseDto(
            id
        )
    }
}
