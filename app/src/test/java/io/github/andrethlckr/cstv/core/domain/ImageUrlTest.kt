package io.github.andrethlckr.cstv.core.domain

import io.kotest.matchers.nulls.shouldBeNull
import io.kotest.matchers.nulls.shouldNotBeNull
import org.junit.Test

class ImageUrlTest {

    @Test
    fun `from should return ImageUrl when text is not null`() {
        val text = "www.image.com/pic"

        val imageUrl = ImageUrl.from(text)

        imageUrl.shouldNotBeNull()
    }

    @Test
    fun `from should return null when text is null`() {
        val text: String? = null

        val imageUrl = ImageUrl.from(text)

        imageUrl.shouldBeNull()
    }
}
