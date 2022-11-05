package com.kakaopay.honey.fixture

import com.kakaopay.honey.application.dto.SearchHistoryCondition
import java.time.LocalDate

object SearchPointHistoryFixture {
    fun getSearchPointHistoryCondition(
        membershipCode: String = "1234567891"
    ): SearchHistoryCondition {
        return SearchHistoryCondition(
            membershipCode = membershipCode,
            startAt = LocalDate.now(),
            endAt = LocalDate.now(),
            page = 1,
            size = 50
        )
    }
}
