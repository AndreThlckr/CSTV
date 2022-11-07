package io.github.andrethlckr.cstv.match.domain

import java.time.ZonedDateTime

@JvmInline
value class MatchId(
    val value: Long
)

data class Match(
    val id: MatchId,
    val name: String,
    val status: MatchStatus,
    val scheduledAt: ZonedDateTime?,
    val teams: Pair<Team?, Team?>,
    val league: League,
    val series: Series
) {

    fun isInProgress() = status == MatchStatus.InProgress
}
