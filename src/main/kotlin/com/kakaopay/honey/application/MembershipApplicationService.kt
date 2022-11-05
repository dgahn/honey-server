package com.kakaopay.honey.application

import com.kakaopay.honey.domain.Membership
import com.kakaopay.honey.domain.MembershipFactory
import com.kakaopay.honey.domain.MembershipJpaRepository
import com.kakaopay.honey.exception.CreateMembershipFailException
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class MembershipApplicationService(
    private val membershipJpaRepository: MembershipJpaRepository,
    private val membershipFactory: MembershipFactory
) {
    @Transactional
    fun createMembership(userId: String): Membership {
        return membershipJpaRepository.findById(userId)
            .orElseGet {
                val membership = createUniqueMembershipCode(userId)
                membershipJpaRepository.save(membership)
            }
    }

    private tailrec fun createUniqueMembershipCode(userId: String, retryTime: Int = RETRY_DEFAULT_TIME): Membership {
        val createdMembership = membershipFactory.create(userId)
        val findMembership = membershipJpaRepository.findByCode(createdMembership.code)
        return if (retryTime == RETRY_MAX_TIME) {
            throw CreateMembershipFailException()
        } else if (findMembership == null) {
            createdMembership
        } else {
            createUniqueMembershipCode(userId, retryTime + RETRY_INCREMENT)
        }
    }

    companion object {
        private const val RETRY_DEFAULT_TIME = 1
        private const val RETRY_MAX_TIME = 5
        private const val RETRY_INCREMENT = 1
    }
}
