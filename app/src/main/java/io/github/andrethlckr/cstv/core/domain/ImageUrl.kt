package io.github.andrethlckr.cstv.core.domain

@JvmInline
value class ImageUrl(
    val url: String
) {

    companion object {

        fun from(text: String?): ImageUrl? = text?.let { ImageUrl(it) }
    }
}
