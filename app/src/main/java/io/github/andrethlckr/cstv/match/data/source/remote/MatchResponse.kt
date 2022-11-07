package io.github.andrethlckr.cstv.match.data.source.remote

import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName

/**
 * Represents a CS:GO match from PandaScore API.
 *
 * The field [players] is only available when searching the match by id.
 */
@Serializable
data class MatchResponse(
    @SerialName("id")
    val id: Long = 0,
    @SerialName("name")
    val name: String = "",
    @SerialName("status")
    val status: String = "",
    @SerialName("scheduled_at")
    val scheduledAt: String? = null,
    @SerialName("opponents")
    val opponents: List<OpponentResponse> = emptyList(),
    @SerialName("league")
    val league: LeagueResponse = LeagueResponse(),
    @SerialName("serie")
    val series: SeriesResponse = SeriesResponse(),
    @SerialName("players")
    val players: List<PlayerResponse> = emptyList(),
)
