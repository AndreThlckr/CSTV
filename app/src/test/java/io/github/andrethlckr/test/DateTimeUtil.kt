package io.github.andrethlckr.test

import java.time.ZoneId
import java.time.ZonedDateTime
import java.util.Locale

object DateTimeUtil {

    fun dateAt(
        year: Int = 2022,
        month: Int = 6,
        day: Int = 10,
        hour: Int = 12,
        minute: Int = 0,
        second: Int = 0,
        nano: Int = 0,
        zone: ZoneId = brazilianZoneId(),
    ) = ZonedDateTime.of(year, month, day, hour, minute, second, nano, zone)

    fun brazilianZoneId() = ZoneId.of("America/Sao_Paulo")

    fun brazilianLocale() = Locale("pt", "BR")
}
