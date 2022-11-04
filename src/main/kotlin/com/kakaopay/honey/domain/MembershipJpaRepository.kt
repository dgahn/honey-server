package com.kakaopay.honey.domain

import org.springframework.data.jpa.repository.JpaRepository

interface MembershipJpaRepository : JpaRepository<Membership, String> {
    fun findByCode(code: String): Membership?
}
