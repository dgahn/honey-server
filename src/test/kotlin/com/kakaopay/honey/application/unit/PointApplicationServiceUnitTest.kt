package com.kakaopay.honey.application.unit

import com.kakaopay.honey.application.PointApplicationService
import com.kakaopay.honey.domain.Category
import com.kakaopay.honey.domain.MembershipJpaRepository
import com.kakaopay.honey.domain.Partner
import com.kakaopay.honey.domain.PartnerJpaRepository
import com.kakaopay.honey.domain.PointHistoryJpaRepository
import com.kakaopay.honey.domain.PointJpaRepository
import com.kakaopay.honey.exception.HoneyNotFoundException
import com.kakaopay.honey.fixture.MembershipFixture
import com.kakaopay.honey.fixture.PointFixture
import com.kakaopay.honey.fixture.PointHistoryFixture
import com.ninjasquad.springmockk.MockkBean
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.matchers.shouldBe
import io.mockk.every
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Import
import org.springframework.data.repository.findByIdOrNull
import org.springframework.test.context.junit.jupiter.SpringExtension

@ExtendWith(SpringExtension::class)
@Import(value = [PointApplicationService::class])
class PointApplicationServiceUnitTest(
    @Autowired private val pointApplicationService: PointApplicationService
) {
    @MockkBean
    lateinit var pointJpaRepository: PointJpaRepository

    @MockkBean
    lateinit var partnerJpaRepository: PartnerJpaRepository

    @MockkBean
    lateinit var membershipJpaRepository: MembershipJpaRepository

    @MockkBean
    lateinit var pointHistoryJpaRepository: PointHistoryJpaRepository

    @Test
    fun `포인트를_적립할_수_있다`() {
        val expected = PointFixture.getPoint()
        expected.earn(100)
        every { partnerJpaRepository.findByIdOrNull(any()) } returns Partner(1L, "partner_1", Category.A)
        every { membershipJpaRepository.findByCode(any()) } returns MembershipFixture.getMembership()
        every { pointJpaRepository.findByCategoryAndMembershipCode(any(), any()) } returns null
        every { pointJpaRepository.save(any()) } returns expected
        every { pointHistoryJpaRepository.save(any()) } returns PointHistoryFixture.getPointHistory()
        val actual = pointApplicationService.earnPoint(100, "1234567891", 1L)
        actual shouldBe expected
    }

    @Test
    fun `등록되지_않은_상점으로_포인트를_적립하려고_하면_HoneyNotFoundException이_발생한다`() {
        every { partnerJpaRepository.findByIdOrNull(any()) } returns null

        shouldThrow<HoneyNotFoundException> {
            pointApplicationService.earnPoint(100, "1234567891", 1L)
        }
    }

    @Test
    fun `등록되지_않은_멤버십_코드로_포인트를_적립하려고_하면_HoneyNotFoundException이_발생한다`() {
        every { partnerJpaRepository.findByIdOrNull(any()) } returns Partner(1L, "partner_1", Category.A)
        every { membershipJpaRepository.findByCode(any()) } returns null

        shouldThrow<HoneyNotFoundException> {
            pointApplicationService.earnPoint(100, "1234567891", 1L)
        }
    }

    @Test
    fun `포인트를_사용할_수_있다`() {
        val original = PointFixture.getPoint(100L)
        val expected = PointFixture.getPoint(50L)
        every { partnerJpaRepository.findByIdOrNull(any()) } returns Partner(1L, "partner_1", Category.A)
        every { membershipJpaRepository.findByCode(any()) } returns MembershipFixture.getMembership()
        every { pointJpaRepository.findByCategoryAndMembershipCode(any(), any()) } returns original
        every { pointJpaRepository.save(any()) } returns expected
        every { pointHistoryJpaRepository.save(any()) } returns PointHistoryFixture.getPointHistory()
        val actual = pointApplicationService.usePoint(50, "1234567891", 1L)
        actual shouldBe expected
    }

    @Test
    fun `등록되지_않은_상점으로_포인트를_사용하려고_하면_HoneyNotFoundException이_발생한다`() {
        every { partnerJpaRepository.findByIdOrNull(any()) } returns null

        shouldThrow<HoneyNotFoundException> {
            pointApplicationService.usePoint(100, "1234567891", 1L)
        }
    }

    @Test
    fun `등록되지_않은_멤버십_코드로_포인트를_사용하려고_하면_HoneyNotFoundException이_발생한다`() {
        every { partnerJpaRepository.findByIdOrNull(any()) } returns Partner(1L, "partner_1", Category.A)
        every { membershipJpaRepository.findByCode(any()) } returns null

        shouldThrow<HoneyNotFoundException> {
            pointApplicationService.usePoint(100, "1234567891", 1L)
        }
    }
}
