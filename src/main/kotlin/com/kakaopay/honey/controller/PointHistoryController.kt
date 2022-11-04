package com.kakaopay.honey.controller

import com.kakaopay.honey.application.PointHistoryApplicationService
import com.kakaopay.honey.controller.dto.SearchPointHistoriesResponseDto
import com.kakaopay.honey.controller.dto.toSearchPointHistoriesResponseDto
import org.springframework.format.annotation.DateTimeFormat
import org.springframework.format.annotation.DateTimeFormat.ISO
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.time.LocalDate

@RestController
class PointHistoryController(
    private val pointHistoryApplicationService: PointHistoryApplicationService
) {

    @GetMapping("/api/v1/point-histories")
    fun searchPointHistories(
        @RequestParam membershipCode: String,
        @RequestParam @DateTimeFormat(iso = ISO.DATE) startAt: LocalDate,
        @RequestParam @DateTimeFormat(iso = ISO.DATE) endAt: LocalDate,
    ): ResponseEntity<SearchPointHistoriesResponseDto> {
        val histories = pointHistoryApplicationService.searchHistories(membershipCode, startAt, endAt)
        return ResponseEntity.ok(histories.toSearchPointHistoriesResponseDto())
    }
}
