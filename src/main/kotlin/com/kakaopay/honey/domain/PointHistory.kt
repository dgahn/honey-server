package com.kakaopay.honey.domain

import org.springframework.data.annotation.CreatedDate
import java.time.Instant
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

@Entity
class PointHistory(
    @Id
    @GeneratedValue
    val id: Long = 0,
    val type: PointEventType,
    val category: Category,
    val membershipCode: String,
    val partnerName: String,
    approvedAt: Instant? = null
) {
    @CreatedDate
    @Column(updatable = false)
    var approvedAt: Instant? = approvedAt
        protected set
}
