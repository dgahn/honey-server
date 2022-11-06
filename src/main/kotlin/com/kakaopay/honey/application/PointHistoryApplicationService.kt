package com.kakaopay.honey.application

import com.kakaopay.honey.application.dto.SearchHistoryCondition
import com.kakaopay.honey.domain.MembershipJpaRepository
import com.kakaopay.honey.domain.PointHistory
import com.kakaopay.honey.domain.PointHistoryJpaRepository
import com.kakaopay.honey.exception.HoneyNotFoundException
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class PointHistoryApplicationService(
    private val pointHistoryJpaRepository: PointHistoryJpaRepository,
    private val membershipJpaRepository: MembershipJpaRepository
) {
    @Transactional(readOnly = true)
    fun searchHistories(searchCondition: SearchHistoryCondition): List<PointHistory> {
        membershipJpaRepository.findByIdOrNull(searchCondition.membershipCode)
            ?: throw HoneyNotFoundException(
                "등록되지 않은 멤버십 코드입니다. (membershipCode: ${searchCondition.membershipCode})"
            )
        return pointHistoryJpaRepository.searchHistories(
            searchCondition.startAtInstant,
            searchCondition.endAtInstant,
            searchCondition.membershipCode,
            searchCondition.pageRequest
        )
    }
}
