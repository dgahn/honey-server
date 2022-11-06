package com.kakaopay.honey.fixture

import com.kakaopay.honey.domain.Category
import com.kakaopay.honey.domain.Point

object PointFixture {
    fun getPoint(
        totalPoint: Long = 0,
        membershipCode: String = "1234567891",
        category: Category = Category.A
    ): Point {
        return Point(
            category = category, membershipCode = membershipCode
        ).apply {
            this.earn(totalPoint)
        }
    }
}
