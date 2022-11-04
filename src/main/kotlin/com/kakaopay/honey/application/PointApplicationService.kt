package com.kakaopay.honey.application

import com.kakaopay.honey.domain.Point
import com.kakaopay.honey.domain.PointJpaRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class PointApplicationService(
    private val pointJpaRepository: PointJpaRepository
) {
    @Transactional
    fun earnPoint(category: String, toEarnPoint: Long, membershipCode: String, partnerName: String): Point {
        val point = (
            pointJpaRepository.findByCategoryAndMembershipCode(category, membershipCode)
                ?: Point(category = category, membershipCode = membershipCode)
            )

        point.earn(toEarnPoint)
        return pointJpaRepository.save(point)
    }
}
