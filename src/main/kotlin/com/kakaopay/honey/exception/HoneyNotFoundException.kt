package com.kakaopay.honey.exception

data class HoneyNotFoundException(
    override val message: String
) : RuntimeException()
