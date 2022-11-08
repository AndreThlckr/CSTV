package io.github.andrethlckr.cstv.match.ui.details

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.ColorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.capitalize
import androidx.compose.ui.text.intl.Locale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import coil.request.ImageRequest
import io.github.andrethlckr.cstv.R
import io.github.andrethlckr.cstv.core.domain.ImageUrl
import io.github.andrethlckr.cstv.core.ui.date.rememberDateToTextFormatter
import io.github.andrethlckr.cstv.core.ui.theme.CSTVTheme
import io.github.andrethlckr.cstv.core.ui.theme.bolder
import io.github.andrethlckr.cstv.core.ui.theme.placeholder
import io.github.andrethlckr.cstv.core.ui.theme.secondaryText
import io.github.andrethlckr.cstv.core.ui.widget.LoadingIndicator
import io.github.andrethlckr.cstv.match.domain.League
import io.github.andrethlckr.cstv.match.domain.LeagueId
import io.github.andrethlckr.cstv.match.domain.Match
import io.github.andrethlckr.cstv.match.domain.MatchId
import io.github.andrethlckr.cstv.match.domain.MatchStatus
import io.github.andrethlckr.cstv.match.domain.Player
import io.github.andrethlckr.cstv.match.domain.PlayerId
import io.github.andrethlckr.cstv.match.domain.Series
import io.github.andrethlckr.cstv.match.domain.SeriesId
import io.github.andrethlckr.cstv.match.domain.Team
import io.github.andrethlckr.cstv.match.domain.TeamId
import io.github.andrethlckr.cstv.match.ui.widget.MatchTeamsBoard
import java.time.ZonedDateTime

@OptIn(ExperimentalLifecycleComposeApi::class)
@Composable
fun MatchDetailsDestination(
    viewModel: MatchDetailsViewModel,
    onNavigateUp: () -> Unit
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    MatchDetailsScreen(
        state = state,
        onNavigateUp = onNavigateUp
    )
}

@Composable
fun MatchDetailsScreen(
    state: MatchDetailsState,
    onNavigateUp: () -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .fillMaxWidth()
            .background(MaterialTheme.colors.background)
    ) {
        TitleBar(
            league = state.match?.league?.name.orEmpty(),
            series = state.match?.series?.name.orEmpty(),
            onNavigateUp = onNavigateUp
        )

        when {
            state.isLoading -> LoadingIndicator(
                modifier = Modifier
                    .weight(1F)
                    .fillMaxWidth()
            )
            state.match != null -> MatchDetailsContent(match = state.match)
        }
    }
}

@Composable
fun TitleBar(
    league: String,
    series: String,
    onNavigateUp: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 32.dp)
    ) {
        Text(
            text = "$league $series",
            color = MaterialTheme.colors.onBackground,
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.h6,
            modifier = Modifier
                .padding(horizontal = 56.dp)
                .align(Alignment.Center)
        )

        IconButton(
            onClick = onNavigateUp,
            modifier = Modifier
                .padding(start = 16.dp)
                .align(Alignment.CenterStart)
        ) {
            Icon(
                imageVector = Icons.Outlined.ArrowBack,
                tint = MaterialTheme.colors.onBackground,
                contentDescription = "Back"
            )
        }
    }
}

@Composable
private fun ColumnScope.MatchDetailsContent(
    match: Match
): Unit = this.run {
    MatchTeamsBoard(
        first = match.teams.first,
        second = match.teams.second,
        modifier = Modifier.padding(top = 12.dp)
    )
    MatchSchedule(
        date = match.scheduledAt,
        modifier = Modifier.padding(top = 12.dp, bottom = 24.dp)
    )
    PlayersGrid(
        firstTeamPlayers = match.teams.first?.players ?: emptyList(),
        secondTeamPlayers = match.teams.second?.players ?: emptyList()
    )
}

@Composable
fun MatchSchedule(
    date: ZonedDateTime?,
    modifier: Modifier = Modifier
) {
    val formatter = rememberDateToTextFormatter()
    val dateText by remember(date) {
        derivedStateOf {
            if (date == null) "" else formatter.format(date)
        }
    }
    Text(
        text = dateText.capitalize(Locale.current),
        color = MaterialTheme.colors.onBackground,
        style = MaterialTheme.typography.body1.bolder(),
        modifier = modifier
    )
}

@Composable
fun PlayersGrid(
    firstTeamPlayers: List<Player>,
    secondTeamPlayers: List<Player>
) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier
                .weight(1F)
                .padding(end = 8.dp)
        ) {
            firstTeamPlayers.forEach {
                LeftPlayerCard(it)
            }
        }
        Column(
            modifier = Modifier
                .weight(1F)
                .padding(start = 8.dp)
        ) {
            secondTeamPlayers.forEach {
                RightPlayerCard(it)
            }
        }
    }
}

@Composable
fun LeftPlayerCard(
    player: Player
) {
    Box {
        Row(
            horizontalArrangement = Arrangement.Start,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp)
                .clip(shape = RoundedCornerShape(topEnd = 12.dp, bottomEnd = 12.dp))
                .background(color = MaterialTheme.colors.surface)
        ) {
            PlayerName(
                nickname = player.nickname,
                name = player.fullName,
                horizontalAlignment = Alignment.End,
                textAlign = TextAlign.End,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        bottom = 8.dp, start = 8.dp, top = 16.dp, end = 72.dp
                    )
            )
        }

        PlayerImage(
            player.image,
            modifier = Modifier
                .align(alignment = Alignment.TopEnd)
                .padding(end = 12.dp)
                .offset(y = (-4).dp)
        )
    }
}

@Composable
fun RightPlayerCard(
    player: Player
) {
    Box {
        Row(
            horizontalArrangement = Arrangement.End,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp)
                .clip(shape = RoundedCornerShape(topStart = 12.dp, bottomStart = 12.dp))
                .background(color = MaterialTheme.colors.surface)
        ) {
            PlayerName(
                nickname = player.nickname,
                name = player.fullName,
                horizontalAlignment = Alignment.Start,
                textAlign = TextAlign.Start,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        bottom = 8.dp, end = 8.dp, top = 16.dp, start = 72.dp
                    )
            )
        }

        PlayerImage(
            player.image,
            modifier = Modifier
                .align(alignment = Alignment.TopStart)
                .padding(start = 12.dp)
                .offset(y = (-4).dp)
        )
    }
}

@Composable
fun PlayerName(
    nickname: String,
    name: String,
    modifier: Modifier = Modifier,
    horizontalAlignment: Alignment.Horizontal = Alignment.End,
    textAlign: TextAlign = TextAlign.End
) {
    Column(
        horizontalAlignment = horizontalAlignment,
        modifier = modifier
    ) {
        Text(
            text = nickname,
            color = MaterialTheme.colors.onBackground,
            style = MaterialTheme.typography.subtitle1,
            textAlign = textAlign
        )
        Text(
            text = name,
            color = MaterialTheme.colors.secondaryText,
            style = MaterialTheme.typography.body1,
            textAlign = textAlign
        )
    }
}

@Composable
fun PlayerImage(
    imageUrl: ImageUrl?,
    modifier: Modifier = Modifier
) {
    AsyncImage(
        model = ImageRequest.Builder(LocalContext.current)
            .data(imageUrl?.url)
            .crossfade(true)
            .build(),
        placeholder = ColorPainter(MaterialTheme.colors.placeholder),
        error = ColorPainter(MaterialTheme.colors.placeholder),
        fallback = ColorPainter(MaterialTheme.colors.placeholder),
        contentDescription = stringResource(R.string.player_image_description),
        contentScale = ContentScale.Crop,
        modifier = modifier
            .size(48.dp)
            .clip(RoundedCornerShape(8.dp))
            .background(color = MaterialTheme.colors.onSurface)
    )
}

@Preview
@Composable
fun LoadingMatchDetailsScreenPreview() {
    CSTVTheme {
        MatchDetailsScreen(
            state = MatchDetailsState(
                isLoading = true,
                match = null
            ),
            onNavigateUp = { }
        )
    }
}

@Preview
@Composable
fun MatchDetailsScreenPreview() {
    CSTVTheme {
        MatchDetailsScreen(
            state = MatchDetailsState(
                isLoading = false,
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
                            players = listOf(
                                Player(
                                    id = PlayerId(1),
                                    nickname = "FalleN",
                                    firstName = "Gabriel",
                                    lastName = "Toledo",
                                    image = ImageUrl(
                                        url = "https://cdn.pandascore.co/images/team/image/127721/7244.png"
                                    )
                                ),
                                Player(
                                    id = PlayerId(1),
                                    nickname = "FalleN",
                                    firstName = "Gabriel",
                                    lastName = "Toledo",
                                    image = null
                                )
                            )
                        ),
                        Team(
                            id = TeamId(3),
                            name = "Second",
                            image = null,
                            players = listOf(
                                Player(
                                    id = PlayerId(1),
                                    nickname = "FalleN",
                                    firstName = "Gabriel",
                                    lastName = "Toledo",
                                    image = null
                                ),
                                Player(
                                    id = PlayerId(1),
                                    nickname = "FalleN",
                                    firstName = "Gabriel",
                                    lastName = "Toledo",
                                    image = ImageUrl(
                                        url = "https://cdn.pandascore.co/images/team/image/127721/7244.png"
                                    )
                                )
                            )
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
            ),
            onNavigateUp = { }
        )
    }
}
