package io.github.andrethlckr.cstv.match.data.source.remote

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

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
    val series: SeriesResponse = SeriesResponse()
)
