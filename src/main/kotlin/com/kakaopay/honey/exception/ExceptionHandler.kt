package com.kakaopay.honey.exception

import com.kakaopay.honey.util.toJson
import mu.KotlinLogging
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import javax.validation.ConstraintViolationException

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

    @ExceptionHandler(ConstraintViolationException::class)
    fun handleConstraintViolationException(e: ConstraintViolationException): ResponseEntity<ErrorResponseDto> {
        val message = e.constraintViolations
            .map {
                ConstraintErrorDto(
                    it.propertyPath.toString().split(CONSTRAINT_VIOLATION_EXCEPTION_PATH_DELIMITER).last(),
                    it.invalidValue.toString(),
                    it.message
                )
            }
            .toJson()
        logger.error { e.message }
        return ResponseEntity(ErrorResponseDto.of(e, message), HttpStatus.BAD_REQUEST)
    }

    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun handleMethodArgumentNotValidException(e: MethodArgumentNotValidException): ResponseEntity<ErrorResponseDto> {
        val message = e.bindingResult.fieldErrors.toFiledErrorDto().toJson()
        logger.error { e.message }
        return ResponseEntity(ErrorResponseDto.of(e, message), HttpStatus.BAD_REQUEST)
    }

    companion object {
        private val logger = KotlinLogging.logger { }

        private const val CONSTRAINT_VIOLATION_EXCEPTION_PATH_DELIMITER = "."
    }
}
