package com.kakaopay.honey.controller

import com.kakaopay.honey.application.MembershipApplicationService
import com.kakaopay.honey.fixture.CreateMembershipDtoFixture
import com.kakaopay.honey.fixture.MembershipFixture
import com.kakaopay.honey.util.SpringMockMvcTestSupport
import com.kakaopay.honey.util.URI
import com.ninjasquad.springmockk.MockkBean
import io.mockk.every
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.test.context.junit.jupiter.SpringExtension

@ExtendWith(SpringExtension::class)
@WebMvcTest
class MembershipControllerTest : SpringMockMvcTestSupport() {

    @MockkBean
    lateinit var membershipApplicationService: MembershipApplicationService

    @Test
    fun `멤버십_코드를_생성할_수_있다`() {
        every { membershipApplicationService.createMembership(any()) } returns MembershipFixture.getMembership()
        val uri = URI("/api/v1/memberships")
        val request = CreateMembershipDtoFixture.getRequestDto()
        val response = CreateMembershipDtoFixture.getResponseDto()
        mockMvcPostTest(uri, request, response)
    }
}
