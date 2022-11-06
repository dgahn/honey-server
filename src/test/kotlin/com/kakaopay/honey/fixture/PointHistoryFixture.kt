package com.kakaopay.honey.fixture

import com.kakaopay.honey.domain.Category
import com.kakaopay.honey.domain.PointEventType
import com.kakaopay.honey.domain.PointHistory
import java.time.Instant

object PointHistoryFixture {
    fun getPointHistories(listSize: Int = 5): List<PointHistory> {
        return (1..listSize).map {
            PointHistory(
                id = it.toLong(),
                type = PointEventType.EARN,
                category = Category.A,
                membershipCode = "1234567891",
                partnerName = "partnerA_$it",
                approvedAt = Instant.EPOCH
            )
        }
    }

    fun getPointHistory(): PointHistory {
        return PointHistory(
            id = 1L,
            type = PointEventType.EARN,
            category = Category.A,
            membershipCode = "1234567891",
            partnerName = "partnerA",
            approvedAt = Instant.EPOCH
        )
    }
}
