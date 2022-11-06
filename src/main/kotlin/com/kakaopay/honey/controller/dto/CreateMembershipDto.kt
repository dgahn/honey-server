package com.kakaopay.honey.controller.dto

import com.fasterxml.jackson.annotation.JsonProperty
import com.kakaopay.honey.domain.Membership
import javax.validation.constraints.Size

data class CreateMembershipRequestDto(
    @JsonProperty("userId")
    @field:Size(min = 9, max = 9)
    val userId: String
)

data class CreateMembershipResponseDto(
    @field:Size(min = 10, max = 10)
    val membershipCode: String
)

fun Membership.toCreateMembershipResponseDto(): CreateMembershipResponseDto {
    return CreateMembershipResponseDto(
        membershipCode = code
    )
}
