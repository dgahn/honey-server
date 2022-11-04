package com.kakaopay.honey.application

import com.kakaopay.honey.domain.PointJpaRepository
import com.kakaopay.honey.fixture.PointFixture
import com.ninjasquad.springmockk.MockkBean
import io.kotest.matchers.shouldBe
import io.mockk.every
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Import
import org.springframework.test.context.junit.jupiter.SpringExtension

@ExtendWith(SpringExtension::class)
@Import(value = [PointApplicationService::class])
class PointApplicationServiceTest(
    @Autowired private val pointApplicationService: PointApplicationService
) {
    @MockkBean
    lateinit var pointJpaRepository: PointJpaRepository

    @Test
    fun `포인트를_적립할_수_있다`() {
        val expected = PointFixture.getPoint()
        every { pointJpaRepository.findByCategoryAndMembershipCode(any(), any()) } returns null
        every { pointJpaRepository.save(any()) } returns expected
        val actual = pointApplicationService.earnPoint("category", 100, "1234567891", "partnerName")
        actual.earn(100)
        actual shouldBe expected
    }
}
