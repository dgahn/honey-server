package com.kakaopay.honey.controller.dto

import com.fasterxml.jackson.annotation.JsonProperty
import com.kakaopay.honey.domain.Membership


// ToDo Validation 추가 필요
data class CreateMembershipRequestDto(
    @JsonProperty("userId")
    val userId: String
)

data class CreateMembershipResponseDto(
    val membershipCode: String
)

fun Membership.toCreateMembershipResponseDto(): CreateMembershipResponseDto {
    return CreateMembershipResponseDto(
        membershipCode = code
    )
}
