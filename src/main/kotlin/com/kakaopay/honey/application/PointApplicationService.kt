package com.kakaopay.honey.application

import com.kakaopay.honey.domain.Point
import org.springframework.stereotype.Service

@Service
class PointApplicationService {
    fun earnPoint(category: String, point: Int, membershipCode: String, partnerName: String): Point {
        throw NotImplementedError("")
    }
}
