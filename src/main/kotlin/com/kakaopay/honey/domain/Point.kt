package com.kakaopay.honey.domain

import javax.persistence.Entity
import javax.persistence.EnumType
import javax.persistence.Enumerated
import javax.persistence.Id

@Entity
class Point(
    @Id
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

    fun use(toUsePoint: Long) {
        check(totalPoint > toUsePoint) { "현재 저장 중인 포인트가 사용 포인트보다 많아야 합니다." }
        totalPoint -= toUsePoint
    }
}
