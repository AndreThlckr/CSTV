package io.github.andrethlckr.cstv.match.domain

import io.github.andrethlckr.cstv.core.domain.ImageUrl

data class Series(
    val id: Int,
    val name: String,
    val image: ImageUrl?
)
