package com.kakaopay.honey.domain

import org.springframework.stereotype.Component
import kotlin.math.pow

@Component
class MembershipFactory {

    fun create(userId: String): Membership {
        val code = Math.random() * CODE_EXPONENT.pow(CODE_LENGTH)
        return Membership(code.toInt().toString(), userId)
    }

    companion object {
        private const val CODE_LENGTH = 10
        private const val CODE_EXPONENT = 10.0
    }
}
