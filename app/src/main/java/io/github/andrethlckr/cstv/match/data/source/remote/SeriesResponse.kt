package io.github.andrethlckr.cstv.match.data.source.remote

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SeriesResponse(
    @SerialName("id")
    val id: Long = 0,
    @SerialName("full_name")
    val fullName: String = "",
)
