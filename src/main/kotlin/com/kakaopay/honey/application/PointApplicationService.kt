package com.kakaopay.honey.application

import com.kakaopay.honey.domain.MembershipJpaRepository
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
    private val partnerJpaRepository: PartnerJpaRepository,
    private val membershipJpaRepository: MembershipJpaRepository
) {
    @Transactional
    fun earnPoint(toEarnPoint: Long, membershipCode: String, partnerId: Long): Point {
        val point = getPointByPartnerIdAndMembershipCode(partnerId, membershipCode)

        point.earn(toEarnPoint)
        return pointJpaRepository.save(point)
    }

    fun usePoint(toUsePoint: Long, membershipCode: String, partnerId: Long): Point {
        val point = getPointByPartnerIdAndMembershipCode(partnerId, membershipCode)

        point.use(toUsePoint)
        return pointJpaRepository.save(point)
    }

    private fun getPointByPartnerIdAndMembershipCode(partnerId: Long, membershipCode: String): Point {
        val partner = partnerJpaRepository.findByIdOrNull(partnerId) ?: throw HoneyNotFoundException(
            "등록되지 않은 상점입니다. (id: $partnerId)"
        )
        membershipJpaRepository.findByCode(membershipCode)
            ?: throw HoneyNotFoundException("등록되지 않은 멤버십 코드입니다. (membershipCode: $membershipCode)")

        val point =
            pointJpaRepository.findByCategoryAndMembershipCode(partner.category, membershipCode)
                ?: Point(category = partner.category, membershipCode = membershipCode)
        return point
    }
}
