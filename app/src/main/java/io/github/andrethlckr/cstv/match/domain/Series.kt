package io.github.andrethlckr.cstv.match.domain

@JvmInline
value class SeriesId(
    val value: Long
)

data class Series(
    val id: SeriesId,
    val name: String
)
