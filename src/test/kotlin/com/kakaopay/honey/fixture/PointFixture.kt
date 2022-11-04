package com.kakaopay.honey.fixture

import com.kakaopay.honey.domain.Category
import com.kakaopay.honey.domain.Point

object PointFixture {
    fun getPoint(totalPoint: Long = 0): Point {
        return Point(
            id = 1L, category = Category.A, membershipCode = "1234567891"
        ).apply {
            this.earn(totalPoint)
        }
    }
}
