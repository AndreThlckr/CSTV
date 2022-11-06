package io.github.andrethlckr.test.rules

import org.junit.rules.ExternalResource
import java.util.*

class LocaleRule(
    private val locale: Locale
) : ExternalResource() {

    private val originalDefaultLocale = Locale.getDefault()

    override fun before() {
        Locale.setDefault(locale)
    }

    override fun after() {
        Locale.setDefault(originalDefaultLocale)
    }

    companion object {

        fun brazilianLocale() = Locale("pt", "BR")
    }
}
