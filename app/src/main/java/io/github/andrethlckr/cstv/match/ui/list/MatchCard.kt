package io.github.andrethlckr.cstv.match.ui.list

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.capitalize
import androidx.compose.ui.text.intl.Locale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import io.github.andrethlckr.cstv.R
import io.github.andrethlckr.cstv.core.ui.date.rememberDateToTextFormatter
import io.github.andrethlckr.cstv.core.ui.theme.CSTVTheme
import io.github.andrethlckr.cstv.core.ui.theme.bolder
import io.github.andrethlckr.cstv.core.ui.theme.highlight
import io.github.andrethlckr.cstv.match.domain.League
import io.github.andrethlckr.cstv.match.domain.LeagueId
import io.github.andrethlckr.cstv.match.domain.Match
import io.github.andrethlckr.cstv.match.domain.MatchId
import io.github.andrethlckr.cstv.match.domain.MatchStatus
import io.github.andrethlckr.cstv.match.domain.Series
import io.github.andrethlckr.cstv.match.domain.SeriesId
import io.github.andrethlckr.cstv.match.domain.Team
import io.github.andrethlckr.cstv.match.domain.TeamId
import io.github.andrethlckr.cstv.match.ui.widget.MatchTeamsBoard
import java.time.ZonedDateTime

@Composable
fun MatchCard(
    match: Match,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    BoxWithConstraints(
        modifier = modifier
    ) {
        Column(
            modifier = Modifier
                .clip(MaterialTheme.shapes.large)
                .background(MaterialTheme.colors.surface)
                .clickable(onClick = onClick)
                .padding(top = 24.dp)
        ) {
            MatchTeamsBoard(
                first = match.teams.first,
                second = match.teams.second,
                modifier = Modifier.padding(vertical = 16.dp)
            )

            Divider(
                color = MaterialTheme.colors.onSurface.copy(alpha = 0.2F)
            )

            LeagueRow(
                league = match.league,
                series = match.series
            )
        }

        if (match.isInProgress()) {
            LiveIndicator(
                modifier = Modifier.align(Alignment.TopEnd)
            )
        } else if (match.scheduledAt != null) {
            ScheduleIndicator(
                date = match.scheduledAt,
                modifier = Modifier.align(Alignment.TopEnd)
            )
        }
    }
}

@Composable
fun LiveIndicator(
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .background(
                color = MaterialTheme.colors.highlight,
                shape = MaterialTheme.shapes.small
            )
            .padding(8.dp)
    ) {
        Text(
            text = stringResource(R.string.live).uppercase(),
            color = MaterialTheme.colors.onSurface,
            style = MaterialTheme.typography.caption.bolder()
        )
    }
}

@Composable
fun ScheduleIndicator(
    date: ZonedDateTime,
    modifier: Modifier = Modifier
) {
    val formatter = rememberDateToTextFormatter()
    val dateText by remember(date) {
        derivedStateOf {
            formatter.format(date)
        }
    }
    Box(
        modifier = modifier
            .background(
                color = MaterialTheme.colors.onSurface.copy(alpha = 0.5F),
                shape = MaterialTheme.shapes.small
            )
            .padding(8.dp)
    ) {
        Text(
            text = dateText.capitalize(Locale.current),
            color = MaterialTheme.colors.onSurface,
            style = MaterialTheme.typography.caption.bolder()
        )
    }
}

@Composable
fun LeagueRow(
    league: League,
    series: Series
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 16.dp, top = 16.dp, bottom = 16.dp)
    ) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(league.image?.url)
                .crossfade(true)
                .build(),
            placeholder = painterResource(R.drawable.circle_placeholder),
            error = painterResource(R.drawable.circle_placeholder),
            fallback = painterResource(R.drawable.circle_placeholder),
            contentDescription = stringResource(R.string.league_emblem_description),
            contentScale = ContentScale.Fit,
            modifier = Modifier
                .size(16.dp)
        )

        Text(
            text = "${league.name} ${series.name}",
            color = MaterialTheme.colors.onSurface,
            style = MaterialTheme.typography.caption,
            modifier = Modifier.padding(start = 8.dp)
        )
    }
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
                teams = Pair(
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
                league = League(
                    id = LeagueId(4),
                    name = "League",
                    image = null
                ),
                series = Series(
                    id = SeriesId(5),
                    name = "Series"
                ),
            ),
            onClick = { }
        )
    }
}

@Preview
@Composable
fun LiveIndicatorPreview() {
    CSTVTheme {
        LiveIndicator()
    }
}

@Preview
@Composable
fun ScheduleTodayIndicatorPreview() {
    CSTVTheme {
        ScheduleIndicator(
            date = ZonedDateTime.now()
        )
    }
}

@Preview
@Composable
fun ScheduleTomorrowIndicatorPreview() {
    CSTVTheme {
        ScheduleIndicator(
            date = ZonedDateTime.now().plusDays(1)
        )
    }
}

@Preview
@Composable
fun ScheduleNextWeekIndicatorPreview() {
    CSTVTheme {
        ScheduleIndicator(
            date = ZonedDateTime.now().plusDays(7)
        )
    }
}


