package io.github.andrethlckr.cstv.match.domain

import java.time.ZonedDateTime

data class Match(
    val id: Int,
    val name: String,
    val status: MatchStatus,
    val beginAt: ZonedDateTime,
    val opponents: List<Opponent>,
    val league: League,
    val series: Series
)
