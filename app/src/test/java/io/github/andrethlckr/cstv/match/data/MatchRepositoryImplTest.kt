package io.github.andrethlckr.cstv.match.data

import io.github.andrethlckr.cstv.core.data.NetworkResult
import io.github.andrethlckr.cstv.core.data.dataOrNull
import io.github.andrethlckr.cstv.core.domain.ImageUrl
import io.github.andrethlckr.cstv.match.data.source.remote.LeagueResponse
import io.github.andrethlckr.cstv.match.data.source.remote.MatchResponse
import io.github.andrethlckr.cstv.match.data.source.remote.OpponentDetailsResponse
import io.github.andrethlckr.cstv.match.data.source.remote.OpponentResponse
import io.github.andrethlckr.cstv.match.data.source.remote.SeriesResponse
import io.github.andrethlckr.cstv.match.data.source.remote.service.GetMatchesService
import io.github.andrethlckr.cstv.match.domain.League
import io.github.andrethlckr.cstv.match.domain.LeagueId
import io.github.andrethlckr.cstv.match.domain.MatchId
import io.github.andrethlckr.cstv.match.domain.MatchRepository
import io.github.andrethlckr.cstv.match.domain.MatchStatus
import io.github.andrethlckr.cstv.match.domain.Opponent
import io.github.andrethlckr.cstv.match.domain.OpponentId
import io.github.andrethlckr.cstv.match.domain.Series
import io.github.andrethlckr.cstv.match.domain.SeriesId
import io.kotest.matchers.collections.shouldNotBeEmpty
import io.kotest.matchers.shouldBe
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Test
import java.time.ZoneOffset
import java.time.ZonedDateTime

@OptIn(ExperimentalCoroutinesApi::class)
class MatchRepositoryImplTest {

    private val getMatchesService: GetMatchesService = mockk()

    private val repository: MatchRepository = MatchRepositoryImpl(
        getMatchesService = getMatchesService
    )

    @Test
    fun `getMatches should return list of matches`() = runTest {
        coEvery { getMatchesService.fetchUpcomingMatches() }
            .returns(NetworkResult.Success(listOf(MatchResponse())))

        val matches = repository.getMatches().dataOrNull()

        matches.shouldNotBeEmpty()
    }

    @Test
    fun `getMatches should return matches with correct name and id`() = runTest {
        coEvery { getMatchesService.fetchUpcomingMatches() } returns NetworkResult.Success(
            listOf(
                MatchResponse(
                    id = 5,
                    name = "Epic match"
                )
            )
        )

        val match = repository.getMatches().dataOrNull()!!.first()

        match.id shouldBe MatchId(5)
        match.name shouldBe "Epic match"
    }

    @Test
    fun `getMatches should return matches with correct status`() = runTest {
        coEvery { getMatchesService.fetchUpcomingMatches() } returns NetworkResult.Success(
            listOf(
                MatchResponse(status = "not_started"),
                MatchResponse(status = "finished"),
                MatchResponse(status = "random"),
                MatchResponse(status = "running")
            )
        )

        val matchStatuses = repository.getMatches().dataOrNull()!!.map { it.status }

        matchStatuses shouldBe listOf(
            MatchStatus.NotStarted,
            MatchStatus.Finished,
            MatchStatus.NotStarted,
            MatchStatus.InProgress
        )
    }

    @Test
    fun `getMatches should return matches with correct schedule`() = runTest {
        coEvery { getMatchesService.fetchUpcomingMatches() } returns NetworkResult.Success(
            listOf(
                MatchResponse(scheduledAt = "2022-10-20T18:15:00Z"),
                MatchResponse(scheduledAt = null)
            )
        )

        val schedules = repository.getMatches().dataOrNull()!!.map { it.scheduledAt }

        schedules shouldBe listOf(
            ZonedDateTime.of(2022, 10, 20, 18, 15, 0, 0, ZoneOffset.UTC),
            null,
        )
    }

    @Test
    fun `getMatches should return matches with correct opponents`() = runTest {
        coEvery { getMatchesService.fetchUpcomingMatches() } returns NetworkResult.Success(
            listOf(
                MatchResponse(
                    opponents = listOf(
                        OpponentResponse(
                            opponent = OpponentDetailsResponse(
                                id = 3,
                                name = "Red Team",
                                imageUrl = "www.red-team.com/pic"
                            )
                        ),
                        OpponentResponse(
                            opponent = OpponentDetailsResponse(
                                id = 7,
                                name = "Blue Team",
                                imageUrl = null
                            )
                        )
                    )
                )
            )
        )

        val opponents = repository.getMatches().dataOrNull()!!.first().opponents

        opponents shouldBe Pair(
            Opponent(
                id = OpponentId(3),
                name = "Red Team",
                image = ImageUrl("www.red-team.com/pic")
            ),
            Opponent(
                id = OpponentId(7),
                name = "Blue Team",
                image = null
            )
        )
    }

    @Test
    fun `getMatches should return matches with default opponent if list is empty`() = runTest {
        coEvery { getMatchesService.fetchUpcomingMatches() } returns NetworkResult.Success(
            listOf(
                MatchResponse(
                    opponents = emptyList()
                )
            )
        )

        val opponents = repository.getMatches().dataOrNull()!!.first().opponents

        opponents shouldBe Pair(
            Opponent(
                id = OpponentId(0),
                name = "",
                image = null
            ),
            Opponent(
                id = OpponentId(0),
                name = "",
                image = null
            )
        )
    }

    @Test
    fun `getMatches should return matches with correct league`() = runTest {
        coEvery { getMatchesService.fetchUpcomingMatches() } returns NetworkResult.Success(
            listOf(
                MatchResponse(
                    league = LeagueResponse(
                        id = 4,
                        name = "Pro League",
                        imageUrl = "www.pro-league.com/pic"
                    )
                )
            )
        )

        val league = repository.getMatches().dataOrNull()!!.first().league

        league shouldBe League(
            id = LeagueId(4),
            name = "Pro League",
            image = ImageUrl("www.pro-league.com/pic")
        )
    }

    @Test
    fun `getMatches should return matches with correct series`() = runTest {
        coEvery { getMatchesService.fetchUpcomingMatches() } returns NetworkResult.Success(
            listOf(
                MatchResponse(
                    series = SeriesResponse(
                        id = 4,
                        fullName = "Spring 2022",
                    )
                )
            )
        )

        val league = repository.getMatches().dataOrNull()!!.first().series

        league shouldBe Series(
            id = SeriesId(4),
            name = "Spring 2022"
        )
    }
}
