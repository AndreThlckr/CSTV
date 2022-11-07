package io.github.andrethlckr.cstv.match.domain

import io.github.andrethlckr.cstv.core.domain.ImageUrl

@JvmInline
value class TeamId(
    val value: Long
)

data class Team(
    val id: TeamId,
    val name: String,
    val image: ImageUrl?
)
