package com.kakaopay.honey.application

import com.kakaopay.honey.domain.PointHistory
import com.kakaopay.honey.domain.PointHistoryJpaRepository
import com.kakaopay.honey.util.toInstant
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDate

@Service
class PointHistoryApplicationService(
    private val pointHistoryJpaRepository: PointHistoryJpaRepository
) {
    @Transactional(readOnly = true)
    fun searchHistories(membershipCode: String, startAt: LocalDate, endAt: LocalDate): List<PointHistory> {
        return pointHistoryJpaRepository.searchHistories(startAt.toInstant(), endAt.toInstant(), membershipCode)
    }
}
