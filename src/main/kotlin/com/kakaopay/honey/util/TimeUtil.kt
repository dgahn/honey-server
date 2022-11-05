package com.kakaopay.honey.util

import java.time.Instant
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter

private val KST: ZoneId = ZoneId.of("Asia/Seoul")
private const val DATE_PATTERN_FORMAT = "yyyy-MM-dd"
private const val DATE_TIME_PATTERN_FORMAT = "yyyy-MM-dd HH:mm:ss"

fun Instant.toDateString(zoneId: ZoneId = KST): String {
    val formatter = DateTimeFormatter.ofPattern(DATE_PATTERN_FORMAT)
        .withZone(zoneId)
    return formatter.format(this)
}

fun LocalDate.toInstant(zoneId: ZoneId = KST): Instant {
    return this.atStartOfDay(zoneId).toInstant()
}

fun LocalDateTime.toDateString(zoneId: ZoneId = KST): String {
    val formatter = DateTimeFormatter.ofPattern(DATE_TIME_PATTERN_FORMAT)
        .withZone(zoneId)
    return formatter.format(this)
}
