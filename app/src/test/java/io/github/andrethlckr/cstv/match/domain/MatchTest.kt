package io.github.andrethlckr.cstv.match.domain

import io.github.andrethlckr.test.DateTimeUtil.dateAt
import io.kotest.matchers.shouldBe
import org.junit.Test
import java.time.ZonedDateTime

class MatchTest {

    @Test
    fun `isInProgress should return true when status is InProgress`() {
        val match = matchWith(status = MatchStatus.InProgress)

        match.isInProgress() shouldBe true
    }

    @Test
    fun `isInProgress should return false when status is not InProgress`() {
        val match = matchWith(status = MatchStatus.Finished)

        match.isInProgress() shouldBe false
    }

    @Test
    fun `title should return match league and series`() {
        val match = matchWith(
            league = League(
                id = LeagueId(4),
                name = "The League",
                image = null
            ),
            series = Series(
                id = SeriesId(5),
                name = "2022 Series"
            )
        )

        match.title() shouldBe "The League 2022 Series"
    }

    companion object {

        fun matchWith(
            id: MatchId = MatchId(1),
            name: String = "Match name",
            status: MatchStatus = MatchStatus.NotStarted,
            scheduledAt: ZonedDateTime = dateAt(year = 2022),
            teams: Pair<Team, Team> = Pair(
                Team(
                    id = TeamId(2),
                    name = "First",
                    image = null,
                    players = null
                ),
                Team(
                    id = TeamId(3),
                    name = "Second",
                    image = null,
                    players = null
                )
            ),
            league: League = League(
                id = LeagueId(4),
                name = "League",
                image = null
            ),
            series: Series = Series(
                id = SeriesId(5),
                name = "Series"
            ),
        ) = Match(
            id = id,
            name = name,
            status = status,
            scheduledAt = scheduledAt,
            teams = teams,
            league = league,
            series = series
        )
    }
}
