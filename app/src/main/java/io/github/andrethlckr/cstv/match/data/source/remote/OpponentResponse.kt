package io.github.andrethlckr.cstv.match.data.source.remote

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class OpponentResponse(
    @SerialName("opponent")
    val opponent: OpponentDetailsResponse = OpponentDetailsResponse(),
)

@Serializable
data class OpponentDetailsResponse(
    @SerialName("id")
    val id: Int = 0,
    @SerialName("image_url")
    val imageUrl: String? = null,
    @SerialName("name")
    val name: String? = null,
)
