package com.kakaopay.honey.fixture

import com.kakaopay.honey.controller.dto.CreateMembershipRequestDto
import com.kakaopay.honey.controller.dto.CreateMembershipResponseDto

object CreateMembershipDtoFixture {
    fun getRequestDto(): CreateMembershipRequestDto {
        return CreateMembershipRequestDto("986017201")
    }

    fun getResponseDto(): CreateMembershipResponseDto {
        return CreateMembershipResponseDto("9860172011")
    }
}
