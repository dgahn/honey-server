package com.kakaopay.honey.exception

// ToDo 예외에 대해서 상태 처리하는 로직 추가
data class HoneyNotFoundException(
    override val message: String
) : RuntimeException()
