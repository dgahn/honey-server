package com.kakaopay.honey.application

import com.kakaopay.honey.domain.PointHistory
import org.springframework.stereotype.Service
import java.time.LocalDate

@Service
class PointHistoryApplicationService {
    fun searchHistories(membershipCode: String, startAt: LocalDate, endAt: LocalDate): List<PointHistory> {
        TODO("Not yet implemented")
    }
}
