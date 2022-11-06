package com.kakaopay.honey.domain

import org.springframework.data.jpa.repository.JpaRepository

interface MembershipJpaRepository : JpaRepository<Membership, String> {
    fun findByUserId(userId: String): Membership?
}
