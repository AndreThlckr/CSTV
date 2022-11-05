package io.github.andrethlckr.cstv.match.domain

import io.github.andrethlckr.cstv.core.domain.ImageUrl

@JvmInline
value class OpponentId(
    val value: Long
)

data class Opponent(
    val id: OpponentId,
    val name: String,
    val image: ImageUrl?
)
