package com.kakaopay.honey.domain

import org.springframework.data.jpa.repository.JpaRepository

interface PointJpaRepository : JpaRepository<Point, String> {
    fun findByCategoryAndMembershipCode(
        category: String,
        membershipCode: String
    ): Point?
}
