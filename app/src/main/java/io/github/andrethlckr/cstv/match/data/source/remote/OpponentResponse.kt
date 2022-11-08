package io.github.andrethlckr.cstv.match.data.source.remote

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class OpponentsResponse(
    @SerialName("opponents")
    val teams: List<TeamResponse> = emptyList(),
)

@Serializable
data class OpponentResponse(
    @SerialName("opponent")
    val team: TeamResponse = TeamResponse(),
)

/**
 * Represents a CS:GO team from PandaScore API.
 *
 * The field [players] is only available when searching the teams directly.
 */
@Serializable
data class TeamResponse(
    @SerialName("id")
    val id: Long = 0,
    @SerialName("name")
    val name: String = "",
    @SerialName("image_url")
    val imageUrl: String? = null,
    @SerialName("players")
    val players: List<PlayerResponse>? = null
)
