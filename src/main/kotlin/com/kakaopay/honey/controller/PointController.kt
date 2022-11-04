package com.kakaopay.honey.controller

import com.kakaopay.honey.application.PointApplicationService
import com.kakaopay.honey.controller.dto.EarnPointRequestDto
import com.kakaopay.honey.controller.dto.EarnPointResponseDto
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class PointController(
    private val pointApplicationService: PointApplicationService
) {

    @PostMapping("/api/v1/point/earn")
    fun earnPoint(@RequestBody request: EarnPointRequestDto): ResponseEntity<EarnPointResponseDto> {
        val point = pointApplicationService.earnPoint(
            request.point,
            request.membershipCode,
            request.partnerId
        )
        return ResponseEntity.ok(EarnPointResponseDto(point.id))
    }
}
