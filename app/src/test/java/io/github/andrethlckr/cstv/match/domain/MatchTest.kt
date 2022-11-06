package io.github.andrethlckr.cstv.match.domain

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

    companion object {

        fun matchWith(
            id: MatchId = MatchId(1),
            name: String = "Match name",
            status: MatchStatus = MatchStatus.NotStarted,
            scheduledAt: ZonedDateTime = ZonedDateTime.now(),
            opponents: Pair<Opponent, Opponent> = Pair(
                Opponent(
                    id = OpponentId(2),
                    name = "First",
                    image = null
                ),
                Opponent(
                    id = OpponentId(3),
                    name = "Second",
                    image = null
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
            opponents = opponents,
            league = league,
            series = series
        )
    }
}
