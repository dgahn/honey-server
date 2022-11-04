package com.kakaopay.honey.application

import com.kakaopay.honey.domain.Category
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
        val point = getPointByPartnerIdAndMembershipCode(partnerId, membershipCode) { category ->
            Point(category = category, membershipCode = membershipCode)
        }

        point.earn(toEarnPoint)
        return pointJpaRepository.save(point)
    }

    @Transactional
    fun usePoint(toUsePoint: Long, membershipCode: String, partnerId: Long): Point {
        val point = getPointByPartnerIdAndMembershipCode(partnerId, membershipCode) { category ->
            throw HoneyNotFoundException("적립한 포인트가 존재하지 않습니다. (membershipCode: $membershipCode, category: $category)")
        }

        point.use(toUsePoint)
        return pointJpaRepository.save(point)
    }

    private fun getPointByPartnerIdAndMembershipCode(
        partnerId: Long,
        membershipCode: String,
        pointNotFoundAction: (Category) -> Point
    ): Point {
        val partner = partnerJpaRepository.findByIdOrNull(partnerId) ?: throw HoneyNotFoundException(
            "등록되지 않은 상점입니다. (id: $partnerId)"
        )
        membershipJpaRepository.findByCode(membershipCode)
            ?: throw HoneyNotFoundException("등록되지 않은 멤버십 코드입니다. (membershipCode: $membershipCode)")

        return pointJpaRepository.findByCategoryAndMembershipCode(partner.category, membershipCode)
            ?: pointNotFoundAction(partner.category)
    }
}
