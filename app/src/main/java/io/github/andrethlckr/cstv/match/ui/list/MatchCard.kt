package io.github.andrethlckr.cstv.match.ui.list

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import io.github.andrethlckr.cstv.core.ui.theme.CSTVTheme
import io.github.andrethlckr.cstv.match.domain.League
import io.github.andrethlckr.cstv.match.domain.LeagueId
import io.github.andrethlckr.cstv.match.domain.Match
import io.github.andrethlckr.cstv.match.domain.MatchId
import io.github.andrethlckr.cstv.match.domain.MatchStatus
import io.github.andrethlckr.cstv.match.domain.Opponent
import io.github.andrethlckr.cstv.match.domain.OpponentId
import io.github.andrethlckr.cstv.match.domain.Series
import io.github.andrethlckr.cstv.match.domain.SeriesId
import java.time.ZonedDateTime

@Composable
fun MatchCard(
    match: Match
) {

}

@Preview
@Composable
fun MatchCardPreview() {
    CSTVTheme {
        MatchCard(
            match = Match(
                id = MatchId(1),
                name = "Match name",
                status = MatchStatus.NotStarted,
                scheduledAt = ZonedDateTime.now(),
                opponents = listOf(
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
                league = League(
                    id = LeagueId(4),
                    name = "League",
                    image = null
                ),
                series = Series(
                    id = SeriesId(5),
                    name = "Series"
                ),
            )
        )
    }
}
