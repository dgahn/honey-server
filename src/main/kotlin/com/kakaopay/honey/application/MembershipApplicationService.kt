package com.kakaopay.honey.application

import com.kakaopay.honey.domain.Membership
import com.kakaopay.honey.domain.MembershipFactory
import com.kakaopay.honey.domain.MembershipJpaRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class MembershipApplicationService(
    private val membershipJpaRepository: MembershipJpaRepository,
    private val membershipFactory: MembershipFactory
) {
    @Transactional
    fun createMembership(userId: String): Membership {
        val membership = createUniqueMembershipCode(userId)

        return membershipJpaRepository.save(membership)
    }

    private tailrec fun createUniqueMembershipCode(userId: String, retryTime: Int = RETRY_DEFAULT_TIME): Membership {
        val createdMembership = membershipFactory.create(userId)
        val findMembership = membershipJpaRepository.findByCode(createdMembership.code)
        return if (retryTime == RETRY_MAX_TIME) {
            // Todo 생성 실패 예외 추가 필요.
            throw IllegalStateException("멤버십코드 생성을 실패하였습니다.")
        } else if (findMembership.isEmpty) {
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
