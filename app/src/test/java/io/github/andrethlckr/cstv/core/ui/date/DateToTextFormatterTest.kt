package io.github.andrethlckr.cstv.core.ui.date

import io.github.andrethlckr.test.DateTimeUtil.brazilianLocale
import io.github.andrethlckr.test.DateTimeUtil.brazilianZoneId
import io.github.andrethlckr.test.DateTimeUtil.dateAt
import io.github.andrethlckr.test.rules.LocaleRule
import io.kotest.matchers.shouldBe
import org.junit.Rule
import org.junit.Test
import java.time.Clock
import java.time.ZoneOffset

class DateToTextFormatterTest {

    @get:Rule
    val localeRule = LocaleRule(locale = brazilianLocale())

    // This date is on a Sunday
    private val currentDate = dateAt(month = 6, day = 5)

    private fun formatter() = DateToTextFormatter(
        clock = Clock.fixed(currentDate.toInstant(), brazilianZoneId()),
        todayString = "hoje"
    )

    @Test
    fun `format should display hours today`() {
        val date = dateAt(month = 6, day = 5, hour = 15, minute = 30, zone = ZoneOffset.UTC)

        val text = formatter().format(date)

        text shouldBe "hoje, 12:30"
    }

    @Test
    fun `format should display hours at monday`() {
        val date = dateAt(month = 6, day = 6, hour = 11, minute = 30, zone = ZoneOffset.UTC)

        val text = formatter().format(date)

        text shouldBe "seg, 08:30"
    }

    @Test
    fun `format should display hours at saturday`() {
        val date = dateAt(month = 6, day = 11, hour = 23, minute = 30, zone = ZoneOffset.UTC)

        val text = formatter().format(date)

        text shouldBe "sáb, 20:30"
    }

    @Test
    fun `format should display hours at next sunday`() {
        val date = dateAt(month = 6, day = 13, hour = 1, minute = 0, zone = ZoneOffset.UTC)

        val text = formatter().format(date)

        text shouldBe "12.06 22:00"
    }
}


