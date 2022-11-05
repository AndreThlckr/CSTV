package io.github.andrethlckr.cstv.match.domain

import io.github.andrethlckr.cstv.core.domain.ImageUrl

@JvmInline
value class LeagueId(
    val value: Long
)

data class League(
    val id: LeagueId,
    val name: String,
    val image: ImageUrl?
)
