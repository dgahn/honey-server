package com.kakaopay.honey.util

import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter

private val KST: ZoneId = ZoneId.of("Asia/Seoul")
private const val DATE_PATTERN_FORMAT = "yyyy-MM-dd"

fun Instant.toDateString(zoneId: ZoneId = KST): String {
    val formatter = DateTimeFormatter.ofPattern(DATE_PATTERN_FORMAT)
        .withZone(zoneId)
    return formatter.format(this)
}
