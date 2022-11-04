package com.kakaopay.honey.controller

import com.kakaopay.honey.application.PointApplicationService
import com.kakaopay.honey.fixture.EarnPointFixture
import com.kakaopay.honey.fixture.PointFixture
import com.kakaopay.honey.fixture.UsePointFixture
import com.kakaopay.honey.util.SpringMockMvcTestSupport
import com.kakaopay.honey.util.URI
import com.ninjasquad.springmockk.MockkBean
import io.mockk.every
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.test.context.junit.jupiter.SpringExtension

@ExtendWith(SpringExtension::class)
@WebMvcTest(value = [PointController::class])
class PointControllerTest : SpringMockMvcTestSupport() {

    @MockkBean
    lateinit var pointApplicationService: PointApplicationService

    @Test
    fun `포인트를_적립할_수_있다`() {
        every { pointApplicationService.earnPoint(any(), any(), any()) } returns PointFixture.getPoint()
        val uri = URI("/api/v1/point/earn")
        val request = EarnPointFixture.getRequestDto()
        val response = EarnPointFixture.getResponseDto()
        mockMvcPostTest(uri, request, response)
    }

    @Test
    fun `포인트를_사용할_수_있다`() {
        every { pointApplicationService.usePoint(any(), any(), any()) } returns PointFixture.getPoint()
        val uri = URI("/api/v1/point/use")
        val request = UsePointFixture.getRequestDto()
        val response = UsePointFixture.getResponseDto()
        mockMvcPostTest(uri, request, response)
    }
}
