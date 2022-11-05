package com.kakaopay.honey.application

import com.kakaopay.honey.domain.MembershipJpaRepository
import com.kakaopay.honey.domain.PointHistory
import com.kakaopay.honey.domain.PointHistoryJpaRepository
import com.kakaopay.honey.exception.HoneyNotFoundException
import com.kakaopay.honey.util.toInstant
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDate

@Service
class PointHistoryApplicationService(
    private val pointHistoryJpaRepository: PointHistoryJpaRepository,
    private val membershipJpaRepository: MembershipJpaRepository
) {
    @Transactional(readOnly = true)
    fun searchHistories(membershipCode: String, startAt: LocalDate, endAt: LocalDate): List<PointHistory> {
        membershipJpaRepository.findByCode(membershipCode)
            ?: throw HoneyNotFoundException("등록되지 않은 멤버십 코드입니다. (membershipCode: $membershipCode)")
        return pointHistoryJpaRepository.searchHistories(startAt.toInstant(), endAt.toInstant(), membershipCode)
    }
}
