package io.github.andrethlckr.cstv.match.data

import io.github.andrethlckr.cstv.core.data.NetworkResult
import io.github.andrethlckr.cstv.core.data.mapList
import io.github.andrethlckr.cstv.core.domain.ImageUrl
import io.github.andrethlckr.cstv.match.data.source.remote.LeagueResponse
import io.github.andrethlckr.cstv.match.data.source.remote.MatchResponse
import io.github.andrethlckr.cstv.match.data.source.remote.OpponentResponse
import io.github.andrethlckr.cstv.match.data.source.remote.SeriesResponse
import io.github.andrethlckr.cstv.match.data.source.remote.service.GetMatchesService
import io.github.andrethlckr.cstv.match.domain.League
import io.github.andrethlckr.cstv.match.domain.LeagueId
import io.github.andrethlckr.cstv.match.domain.Match
import io.github.andrethlckr.cstv.match.domain.MatchId
import io.github.andrethlckr.cstv.match.domain.MatchRepository
import io.github.andrethlckr.cstv.match.domain.MatchStatus
import io.github.andrethlckr.cstv.match.domain.Opponent
import io.github.andrethlckr.cstv.match.domain.OpponentId
import io.github.andrethlckr.cstv.match.domain.Series
import io.github.andrethlckr.cstv.match.domain.SeriesId
import java.time.ZonedDateTime
import javax.inject.Inject

class MatchRepositoryImpl @Inject constructor(
    private val getMatchesService: GetMatchesService
) : MatchRepository {

    override suspend fun getMatches(): NetworkResult<List<Match>> = getMatchesService
        .fetchUpcomingMatches()
        .mapList { matchFrom(it) }

    private fun matchFrom(response: MatchResponse) = Match(
        id = MatchId(response.id),
        name = response.name,
        status = statusFrom(response.status),
        scheduledAt = timeFrom(response.scheduledAt),
        opponents = response.opponents.map { opponentFrom(it) },
        league = leagueFrom(response.league),
        series = seriesFrom(response.series)
    )

    private fun statusFrom(text: String) = when(text) {
        "not_started" -> MatchStatus.NotStarted
        "running" -> MatchStatus.InProgress
        "finished" -> MatchStatus.Finished
        else -> MatchStatus.NotStarted
    }

    private fun timeFrom(text: String?) = text?.let { ZonedDateTime.parse(it) }

    private fun opponentFrom(response: OpponentResponse) = Opponent(
        id = OpponentId(response.opponent.id),
        name = response.opponent.name,
        image = ImageUrl.from(response.opponent.imageUrl)
    )

    private fun leagueFrom(response: LeagueResponse) = League(
        id = LeagueId(response.id),
        name = response.name,
        image = ImageUrl.from(response.imageUrl)
    )

    private fun seriesFrom(response: SeriesResponse) = Series(
        id = SeriesId(response.id),
        name = response.fullName,
    )
}
