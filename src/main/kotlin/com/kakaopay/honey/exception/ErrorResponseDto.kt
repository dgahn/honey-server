package com.kakaopay.honey.exception

import com.kakaopay.honey.util.toDateTimeString
import java.time.LocalDateTime

data class ErrorResponseDto(
    val message: String,
    val trace: String,
    val timestamp: String = LocalDateTime.now().toDateTimeString()
) {
    companion object {
        fun of(e: Exception) = ErrorResponseDto(
            message = e.message.orEmpty(),
            trace = e.stackTraceToString()
        )
    }
}
