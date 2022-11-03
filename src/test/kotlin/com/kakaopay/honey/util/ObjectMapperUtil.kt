package com.kakaopay.honey.util

import com.fasterxml.jackson.databind.ObjectMapper

val objectMapper = ObjectMapper()

inline fun <reified T> List<T>.toJson(): String = objectMapper.writeValueAsString(this)

inline fun <reified T> T.toJson(): String = objectMapper.writeValueAsString(this)
