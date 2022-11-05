package com.kakaopay.honey.domain

import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import java.time.Instant

interface PointHistoryJpaRepository : JpaRepository<PointHistory, Long> {
    @Query(
        """
        SELECT ph
          FROM PointHistory ph 
         WHERE ph.membershipCode = :membershipCode
           AND ph.approvedAt BETWEEN :startAt AND :endAt
        """

    )
    fun searchHistories(
        startAt: Instant,
        endAt: Instant,
        membershipCode: String,
        pageable: Pageable
    ): List<PointHistory>
}
