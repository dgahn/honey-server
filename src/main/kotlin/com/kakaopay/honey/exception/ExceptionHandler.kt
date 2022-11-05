package com.kakaopay.honey.exception

import mu.KotlinLogging
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

@ControllerAdvice
class ExceptionHandler {

    @ExceptionHandler(HoneyNotFoundException::class)
    fun handleHoneyNotFoundException(e: HoneyNotFoundException): ResponseEntity<ErrorResponseDto> {
        logger.error { e.message }
        return ResponseEntity(ErrorResponseDto.of(e), HttpStatus.BAD_REQUEST)
    }

    @ExceptionHandler(IllegalStateException::class)
    fun handleIllegalStateException(e: IllegalStateException): ResponseEntity<ErrorResponseDto> {
        logger.error { e.message }
        return ResponseEntity(ErrorResponseDto.of(e), HttpStatus.BAD_REQUEST)
    }

    @ExceptionHandler(IllegalArgumentException::class)
    fun handleIllegalArgumentException(e: IllegalArgumentException): ResponseEntity<ErrorResponseDto> {
        logger.error { e.message }
        return ResponseEntity(ErrorResponseDto.of(e), HttpStatus.BAD_REQUEST)
    }

    @ExceptionHandler(CreateMembershipFailException::class)
    fun handleCreateMembershipFailException(e: CreateMembershipFailException): ResponseEntity<ErrorResponseDto> {
        logger.error { e.message }
        return ResponseEntity(ErrorResponseDto.of(e), HttpStatus.INTERNAL_SERVER_ERROR)
    }

    companion object {
        private val logger = KotlinLogging.logger { }
    }
}
