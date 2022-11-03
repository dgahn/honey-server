package com.kakaopay.honey.application

import com.kakaopay.honey.domain.Membership
import com.kakaopay.honey.domain.MembershipFactory
import com.kakaopay.honey.domain.MembershipJpaRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class MembershipApplicationService(
    private val jpaRepository: MembershipJpaRepository,
    private val membershipFactory: MembershipFactory
) {
    @Transactional
    fun createMembership(userId: String): Membership {
        val membership = membershipFactory.create(userId)
        return jpaRepository.save(membership)
    }
}
