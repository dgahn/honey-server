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
}
