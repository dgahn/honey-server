package com.kakaopay.honey.fixture

import com.kakaopay.honey.domain.Point

object PointFixture {
    fun getPoint(): Point {
        return Point(
            id = 1L, category = "category", membershipCode = "1234567891"
        )
    }
}
