package com.kakaopay.honey.exception

import com.kakaopay.honey.util.toDateString
import java.time.LocalDateTime

data class ErrorResponseDto(
    val message: String,
    val trace: String,
    val timestamp: String = LocalDateTime.now().toDateString()
) {
    companion object {
        fun of(e: Exception) = ErrorResponseDto(
            message = e.message.orEmpty(),
            trace = e.stackTraceToString()
        )
    }
}
