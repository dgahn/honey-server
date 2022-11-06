package com.kakaopay.honey.domain

import org.springframework.data.annotation.Version
import javax.persistence.Entity
import javax.persistence.EnumType
import javax.persistence.Enumerated
import javax.persistence.GeneratedValue
import javax.persistence.Id

@Entity
class Point(
    @Id
    @GeneratedValue
    val id: Long = 0,
    @Enumerated(EnumType.STRING)
    val category: Category,
    val membershipCode: String
) {
    var totalPoint: Long = 0
        protected set

    fun earn(toEarnPoint: Long) {
        totalPoint += toEarnPoint
    }

    @Version
    var version: Long = 1
        protected set

    fun use(toUsePoint: Long) {
        check(totalPoint > toUsePoint) { "현재 저장 중인 포인트가 사용 포인트보다 많아야 합니다." }
        totalPoint -= toUsePoint
    }

    override fun toString(): String {
        return "Point(id=$id, category=$category, membershipCode='$membershipCode', " +
            "totalPoint=$totalPoint, version=$version)"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Point

        if (id != other.id) return false

        return true
    }

    override fun hashCode(): Int {
        return id.hashCode()
    }
}
