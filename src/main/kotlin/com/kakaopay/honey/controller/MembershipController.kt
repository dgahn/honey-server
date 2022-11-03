package com.kakaopay.honey.controller

import com.kakaopay.honey.application.MembershipApplicationService
import com.kakaopay.honey.controller.dto.CreateMembershipRequestDto
import com.kakaopay.honey.controller.dto.CreateMembershipResponseDto
import com.kakaopay.honey.controller.dto.toCreateMembershipResponseDto
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody

@Controller
class MembershipController(
    private val membershipApplicationService: MembershipApplicationService
) {

    @PostMapping("/api/v1/memberships")
    fun createMembership(
        @RequestBody request: CreateMembershipRequestDto
    ): ResponseEntity<CreateMembershipResponseDto> {
        val responseDto = membershipApplicationService.createMembership(request.userId).toCreateMembershipResponseDto()
        return ResponseEntity.ok(responseDto)
    }
}
