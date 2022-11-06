package com.kakaopay.honey.application.unit

import com.kakaopay.honey.application.PointHistoryApplicationService
import com.kakaopay.honey.domain.MembershipJpaRepository
import com.kakaopay.honey.domain.PointHistoryJpaRepository
import com.kakaopay.honey.exception.HoneyNotFoundException
import com.kakaopay.honey.fixture.MembershipFixture
import com.kakaopay.honey.fixture.PointHistoryFixture
import com.kakaopay.honey.fixture.SearchPointHistoryFixture
import com.ninjasquad.springmockk.MockkBean
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.matchers.shouldBe
import io.mockk.every
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Import
import org.springframework.test.context.junit.jupiter.SpringExtension

@ExtendWith(SpringExtension::class)
@Import(value = [PointHistoryApplicationService::class])
class PointHistoryApplicationServiceUnitTest(
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
        every { pointHistoryJpaRepository.searchHistories(any(), any(), any(), any()) } returns expected
        every { membershipJpaRepository.findByCode(any()) } returns MembershipFixture.getMembership()
        val searchCondition = SearchPointHistoryFixture.getSearchPointHistoryCondition(membershipCode)
        val actual = pointHistoryApplicationService.searchHistories(searchCondition)
        actual shouldBe expected
    }

    @Test
    fun `등록되지_않은_멤버십_코드로_포인트_내역을_조회하면_HoneyNotFoundException이_발생한다`() {
        val expected = PointHistoryFixture.getPointHistories()
        val membershipCode = expected.first().membershipCode
        val searchCondition = SearchPointHistoryFixture.getSearchPointHistoryCondition(membershipCode)
        every { membershipJpaRepository.findByCode(any()) } returns null
        shouldThrow<HoneyNotFoundException> {
            pointHistoryApplicationService.searchHistories(searchCondition)
        }
    }
}
