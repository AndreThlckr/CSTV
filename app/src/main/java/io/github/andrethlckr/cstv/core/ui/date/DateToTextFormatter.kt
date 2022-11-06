package io.github.andrethlckr.cstv.core.ui.date

import java.time.Clock
import java.time.LocalDate
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit

class DateToTextFormatter(
    private val clock: Clock = Clock.systemDefaultZone(),
    private val todayString: String
) {

    fun format(date: ZonedDateTime): String {
        val dateOnZone = date.withZoneSameInstant(clock.zone)

        if (isToday(dateOnZone)) {
            val timeString = dateOnZone.format(timeFormat)
            return "$todayString, $timeString"
        }

        if (isSameWeek(dateOnZone)) {
            return dateOnZone.format(weekdayAndTimeFormat)
        }

        return dateOnZone.format(dayMonthAndTimeFormat)
    }

    private fun isToday(date: ZonedDateTime) = date.toLocalDate() == LocalDate.now(clock)

    private fun isSameWeek(date: ZonedDateTime) = ChronoUnit.DAYS.between(LocalDate.now(clock), date) < 7

    companion object {

        private val timeFormat = DateTimeFormatter.ofPattern("HH:mm")
        private val weekdayAndTimeFormat = DateTimeFormatter.ofPattern("EEE, HH:mm")
        private val dayMonthAndTimeFormat = DateTimeFormatter.ofPattern("dd.MM HH:mm")
    }
}
