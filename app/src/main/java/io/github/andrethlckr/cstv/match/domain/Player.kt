package io.github.andrethlckr.cstv.match.domain

import io.github.andrethlckr.cstv.core.domain.ImageUrl

@JvmInline
value class PlayerId(
    val value: Long
)

data class Player(
    val id: PlayerId,
    val nickname: String,
    val firstName: String,
    val lastName: String,
    val image: ImageUrl?
) {

    val fullName = "$firstName $lastName"
}
