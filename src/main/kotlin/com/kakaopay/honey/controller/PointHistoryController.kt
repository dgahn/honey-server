package com.kakaopay.honey.controller

import com.kakaopay.honey.application.PointHistoryApplicationService
import com.kakaopay.honey.application.dto.SearchHistoryCondition
import com.kakaopay.honey.controller.dto.SearchPointHistoriesResponseDto
import com.kakaopay.honey.controller.dto.toSearchPointHistoriesResponseDto
import org.springframework.format.annotation.DateTimeFormat
import org.springframework.format.annotation.DateTimeFormat.ISO
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.time.LocalDate
import javax.validation.constraints.Max
import javax.validation.constraints.Min

@RestController
@Validated
class PointHistoryController(
    private val pointHistoryApplicationService: PointHistoryApplicationService
) {

    @GetMapping("/api/v1/point-histories")
    fun searchPointHistories(
        @RequestParam membershipCode: String,
        @RequestParam @DateTimeFormat(iso = ISO.DATE) startAt: LocalDate,
        @RequestParam @DateTimeFormat(iso = ISO.DATE) endAt: LocalDate,
        @RequestParam(required = false, defaultValue = "1") @Min(PAGING_MIN) @Max(PAGING_MAX) page: Int,
        @RequestParam(required = false, defaultValue = "10") @Min(PAGING_MIN) @Max(PAGING_MAX) size: Int,
    ): ResponseEntity<SearchPointHistoriesResponseDto> {
        val histories = pointHistoryApplicationService.searchHistories(
            SearchHistoryCondition(membershipCode, startAt, endAt, page, size)
        )
        return ResponseEntity.ok(histories.toSearchPointHistoriesResponseDto())
    }

    companion object {
        private const val PAGING_MIN = 1L
        private const val PAGING_MAX = 50L
    }
}
