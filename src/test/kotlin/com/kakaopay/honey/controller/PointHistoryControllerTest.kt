package com.kakaopay.honey.controller

import com.kakaopay.honey.application.PointHistoryApplicationService
import com.kakaopay.honey.controller.dto.toSearchPointHistoriesResponseDto
import com.kakaopay.honey.fixture.PointHistoryFixture
import com.kakaopay.honey.util.SpringMockMvcTestSupport
import com.kakaopay.honey.util.URI
import com.ninjasquad.springmockk.MockkBean
import io.mockk.every
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.test.context.junit.jupiter.SpringExtension

@ExtendWith(SpringExtension::class)
@WebMvcTest(value = [PointHistoryController::class])
class PointHistoryControllerTest : SpringMockMvcTestSupport() {

    @MockkBean
    lateinit var pointHistoryApplicationService: PointHistoryApplicationService

    @Test
    fun `포인트_사용_내역을_조회할_수_있다`() {
        val expectedHistories = PointHistoryFixture.getPointHistories()
        every { pointHistoryApplicationService.searchHistories(any(), any(), any()) } returns expectedHistories
        val uri = URI("/api/v1/point-histories")
        uri.addQueryParam("startAt", "2022-11-04")
        uri.addQueryParam("endAt", "2022-11-05")
        uri.addQueryParam("membershipCode", expectedHistories.first().membershipCode)
        val expectedResponseDto = expectedHistories.toSearchPointHistoriesResponseDto()
        mockMvcGetTest(uri, expectedResponseDto)
    }
}
