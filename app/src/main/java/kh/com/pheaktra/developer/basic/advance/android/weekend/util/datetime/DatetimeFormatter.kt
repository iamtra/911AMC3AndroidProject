package kh.com.pheaktra.developer.basic.advance.android.weekend.util.datetime

import kh.com.pheaktra.developer.basic.advance.android.weekend.util.common.DateFormat
import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter

object DatetimeFormatter {
    fun formatDate(date: Long, pattern: DateFormat,): String {
        return Instant.ofEpochMilli(date)
            .atZone(ZoneId.systemDefault())
            .toLocalDate()
            .format(DateTimeFormatter.ofPattern(pattern.pattern))
    }
}