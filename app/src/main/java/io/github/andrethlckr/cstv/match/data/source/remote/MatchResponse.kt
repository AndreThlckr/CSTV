package io.github.andrethlckr.cstv.match.data.source.remote

import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName

@Serializable
data class MatchResponse(
    @SerialName("id")
    val id: Int = 0,
    @SerialName("name")
    val name: String = "",
    @SerialName("status")
    val status: String = "",
    @SerialName("begin_at")
    val beginAt: String? = null,
    @SerialName("opponents")
    val opponents: List<OpponentResponse> = emptyList(),
    @SerialName("league")
    val league: LeagueResponse = LeagueResponse(),
    @SerialName("serie")
    val series: SeriesResponse = SeriesResponse(),
)
