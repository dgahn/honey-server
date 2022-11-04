package com.kakaopay.honey.domain

import org.springframework.data.jpa.repository.JpaRepository
import java.util.Optional

interface MembershipJpaRepository : JpaRepository<Membership, String> {
    fun findByCode(code: String): Optional<Membership>
}
