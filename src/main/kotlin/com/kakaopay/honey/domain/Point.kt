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
        // ToDo totalPoint가 음수가 되지 않도록 예외 설정 추가
        totalPoint -= toUsePoint
    }
}
