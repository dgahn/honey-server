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

    override fun toString(): String {
        return "PointHistory(id=$id, type=$type, category=$category, membershipCode='$membershipCode', " +
                "partnerName='$partnerName', approvedAt=$approvedAt)"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as PointHistory

        if (id != other.id) return false

        return true
    }

    override fun hashCode(): Int {
        return id.hashCode()
    }
}
