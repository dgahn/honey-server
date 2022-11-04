package com.kakaopay.honey.application

import com.kakaopay.honey.domain.PartnerJpaRepository
import com.kakaopay.honey.domain.Point
import com.kakaopay.honey.domain.PointJpaRepository
import com.kakaopay.honey.exception.HoneyNotFoundException
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class PointApplicationService(
    private val pointJpaRepository: PointJpaRepository,
    private val partnerJpaRepository: PartnerJpaRepository
) {
    @Transactional
    fun earnPoint(toEarnPoint: Long, membershipCode: String, partnerId: Long): Point {
        val partner = partnerJpaRepository.findByIdOrNull(partnerId) ?: throw HoneyNotFoundException(
            "존재하지 않는 상점입니다. (id: $partnerId)"
        )
        val point = (
            pointJpaRepository.findByCategoryAndMembershipCode(partner.category, membershipCode)
                ?: Point(category = partner.category, membershipCode = membershipCode)
            )

        point.earn(toEarnPoint)
        return pointJpaRepository.save(point)
    }
}
