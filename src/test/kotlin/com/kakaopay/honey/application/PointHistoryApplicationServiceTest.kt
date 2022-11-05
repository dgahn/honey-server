package com.kakaopay.honey.application

import com.kakaopay.honey.domain.PointHistoryJpaRepository
import com.kakaopay.honey.fixture.PointHistoryFixture
import com.ninjasquad.springmockk.MockkBean
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

    @Test
    fun `포인트_내역을_조회할_수_있다`() {
        val expected = PointHistoryFixture.getPointHistories()
        val membershipCode = expected.first().membershipCode
        every { pointHistoryJpaRepository.searchHistories(any(), any(), any()) } returns expected
        val actual = pointHistoryApplicationService.searchHistories(membershipCode, LocalDate.now(), LocalDate.now())
        actual shouldBe expected
    }
}
