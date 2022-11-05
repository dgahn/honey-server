package com.kakaopay.honey.application

import com.kakaopay.honey.domain.MembershipJpaRepository
import com.kakaopay.honey.domain.PointHistoryJpaRepository
import com.kakaopay.honey.exception.HoneyNotFoundException
import com.kakaopay.honey.fixture.MembershipFixture
import com.kakaopay.honey.fixture.PointHistoryFixture
import com.ninjasquad.springmockk.MockkBean
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.matchers.shouldBe
import io.mockk.every
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Import
import org.springframework.test.context.junit.jupiter.SpringExtension
import java.time.LocalDate

@ExtendWith(SpringExtension::class)
@Import(value = [PointHistoryApplicationService::class])
class PointHistoryApplicationServiceTest(
    @Autowired private val pointHistoryApplicationService: PointHistoryApplicationService
) {
    @MockkBean
    lateinit var pointHistoryJpaRepository: PointHistoryJpaRepository

    @MockkBean
    lateinit var membershipJpaRepository: MembershipJpaRepository

    @Test
    fun `포인트_내역을_조회할_수_있다`() {
        val expected = PointHistoryFixture.getPointHistories()
        val membershipCode = expected.first().membershipCode
        every { pointHistoryJpaRepository.searchHistories(any(), any(), any()) } returns expected
        every { membershipJpaRepository.findByCode(any()) } returns MembershipFixture.getMembership()
        val actual = pointHistoryApplicationService.searchHistories(membershipCode, LocalDate.now(), LocalDate.now())
        actual shouldBe expected
    }

    @Test
    fun `등록되지_않은_멤버십_코드로_포인트_내역을_조회하면_HoneyNotFoundException이_발생한다`() {
        val expected = PointHistoryFixture.getPointHistories()
        val membershipCode = expected.first().membershipCode
        every { membershipJpaRepository.findByCode(any()) } returns null
        shouldThrow<HoneyNotFoundException> {
            pointHistoryApplicationService.searchHistories(membershipCode, LocalDate.now(), LocalDate.now())
        }
    }
}
