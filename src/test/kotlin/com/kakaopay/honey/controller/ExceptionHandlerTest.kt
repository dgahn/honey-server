package com.kakaopay.honey.controller

import com.kakaopay.honey.application.MembershipApplicationService
import com.kakaopay.honey.exception.ErrorResponseDto
import com.kakaopay.honey.exception.HoneyNotFoundException
import com.kakaopay.honey.fixture.CreateMembershipDtoFixture
import com.kakaopay.honey.util.SpringMockMvcTestSupport
import com.kakaopay.honey.util.URI
import com.ninjasquad.springmockk.MockkBean
import io.mockk.every
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.http.HttpStatus
import org.springframework.test.context.junit.jupiter.SpringExtension

@ExtendWith(SpringExtension::class)
@WebMvcTest(value = [MembershipController::class])
class ExceptionHandlerTest : SpringMockMvcTestSupport() {

    @MockkBean
    lateinit var membershipApplicationService: MembershipApplicationService

    @Test
    fun `HoneyNotfoundException이_발생하면_BadRequest를_반환한다`() {
        val exception = HoneyNotFoundException("message")
        every { membershipApplicationService.createMembership(any()) } throws exception
        val uri = URI("/api/v1/memberships")
        val request = CreateMembershipDtoFixture.getRequestDto()
        mockMvcPostTest(
            uri = uri,
            requestDto = request,
            expectedResponseDto = ErrorResponseDto.of(exception),
            status = HttpStatus.BAD_REQUEST
        )
    }
}
