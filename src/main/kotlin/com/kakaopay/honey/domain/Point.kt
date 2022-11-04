package com.kakaopay.honey.domain

import javax.persistence.Entity
import javax.persistence.Id

@Entity
class Point(
    @Id
    val id: Long = 0,
    val category: String,
    val membershipCode: String
) {
    var totalPoint: Long = 0
        protected set

    fun earn(toEarnPoint: Long) {
        totalPoint += toEarnPoint
    }
}
