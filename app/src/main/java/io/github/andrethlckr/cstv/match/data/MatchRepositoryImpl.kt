package io.github.andrethlckr.cstv.match.data

import io.github.andrethlckr.cstv.core.data.NetworkResult
import io.github.andrethlckr.cstv.core.data.map
import io.github.andrethlckr.cstv.core.data.mapList
import io.github.andrethlckr.cstv.core.domain.ImageUrl
import io.github.andrethlckr.cstv.match.data.source.remote.LeagueResponse
import io.github.andrethlckr.cstv.match.data.source.remote.MatchResponse
import io.github.andrethlckr.cstv.match.data.source.remote.OpponentResponse
import io.github.andrethlckr.cstv.match.data.source.remote.PlayerResponse
import io.github.andrethlckr.cstv.match.data.source.remote.SeriesResponse
import io.github.andrethlckr.cstv.match.data.source.remote.TeamResponse
import io.github.andrethlckr.cstv.match.data.source.remote.service.GetMatchDetailsService
import io.github.andrethlckr.cstv.match.data.source.remote.service.GetMatchesService
import io.github.andrethlckr.cstv.match.domain.League
import io.github.andrethlckr.cstv.match.domain.LeagueId
import io.github.andrethlckr.cstv.match.domain.Match
import io.github.andrethlckr.cstv.match.domain.MatchId
import io.github.andrethlckr.cstv.match.domain.MatchRepository
import io.github.andrethlckr.cstv.match.domain.MatchStatus
import io.github.andrethlckr.cstv.match.domain.Player
import io.github.andrethlckr.cstv.match.domain.PlayerId
import io.github.andrethlckr.cstv.match.domain.Team
import io.github.andrethlckr.cstv.match.domain.TeamId
import io.github.andrethlckr.cstv.match.domain.Series
import io.github.andrethlckr.cstv.match.domain.SeriesId
import java.time.ZonedDateTime
import javax.inject.Inject

class MatchRepositoryImpl @Inject constructor(
    private val getMatchesService: GetMatchesService,
    private val getMatchDetailsService: GetMatchDetailsService
) : MatchRepository {

    override suspend fun getMatches(): NetworkResult<List<Match>> = getMatchesService
        .fetchUpcomingMatches()
        .mapList { matchFrom(it) }

    override suspend fun getMatchDetails(id: MatchId): NetworkResult<Match> = getMatchDetailsService
        .fetchMatchDetails(id.value)
        .map { matchFrom(it) }

    private fun matchFrom(response: MatchResponse) = Match(
        id = MatchId(response.id),
        name = response.name,
        status = statusFrom(response.status),
        scheduledAt = timeFrom(response.scheduledAt),
        teams = teamsFrom(
            opponents = response.opponents,
            players = response.players
        ),
        league = leagueFrom(response.league),
        series = seriesFrom(response.series)
    )

    private fun statusFrom(text: String) = when (text) {
        "not_started" -> MatchStatus.NotStarted
        "running" -> MatchStatus.InProgress
        "finished" -> MatchStatus.Finished
        else -> MatchStatus.NotStarted
    }

    private fun timeFrom(text: String?) = text?.let { ZonedDateTime.parse(it) }

    private fun teamsFrom(
        opponents: List<OpponentResponse>,
        players: List<PlayerResponse>?
    ) = Pair(
        first = opponentFrom(
            team = opponents.getOrNull(0)?.team,
            players = players?.take(players.size / 2)
        ),
        second = opponentFrom(
            team = opponents.getOrNull(1)?.team,
            players = players?.takeLast(players.size / 2)
        )
    )

    private fun opponentFrom(
        team: TeamResponse?,
        players: List<PlayerResponse>?
    ) = team?.let {
        Team(
            id = TeamId(team.id),
            name = team.name,
            image = ImageUrl.from(team.imageUrl),
            players = players?.map { response -> playerFrom(response) }
        )
    }

    private fun playerFrom(response: PlayerResponse) = Player(
        id = PlayerId(response.id),
        nickname = response.nickname,
        firstName = response.firstName,
        lastName = response.lastName,
        image = ImageUrl.from(response.imageUrl)
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
