package com.kakaopay.honey.domain

import com.kakaopay.honey.fixture.PointFixture
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class PointTest {
    @Test
    fun `포인트는_사용한_만큼_차감된다`() {
        val point = PointFixture.getPoint()
        point.earn(100)
        point.use(50)
        point.totalPoint shouldBe 50
    }

    @Test
    fun `현재_포인트보다_많은_포인트를_사용할_수_없다`() {
        val point = PointFixture.getPoint()
        point.earn(100)
        shouldThrow<IllegalStateException> {
            point.use(100)
        }
    }
}
