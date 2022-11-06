package io.github.andrethlckr.cstv.match.ui.list

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import io.github.andrethlckr.cstv.R
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

@OptIn(ExperimentalLifecycleComposeApi::class)
@Composable
fun MatchListDestination(
    viewModel: MatchListViewModel
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    MatchListScreen(
        isLoading = state.isLoading,
        matches = state.matches
    )
}

@Composable
fun MatchListScreen(
    isLoading: Boolean,
    matches: List<Match>
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.background)
            .padding(horizontal = 24.dp)
    ) {
        if (isLoading) {
            item(
                contentType = { "loading" }
            ) {
                LoadingIndicator(
                    modifier = Modifier.fillParentMaxSize()
                )
            }
        } else {
            item(
                contentType = { "title" }
            ) {
                Text(
                    text = stringResource(R.string.matches),
                    style = MaterialTheme.typography.h2,
                    color = contentColorFor(MaterialTheme.colors.background),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 24.dp)
                )
            }

            items(
                items = matches,
                key = { it.id.value }
            ) {
                MatchCard(
                    match = it,
                    modifier = Modifier.padding(bottom = 24.dp)
                )
            }
        }
    }
}


@Composable
fun LoadingIndicator(
    modifier: Modifier = Modifier
) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {
        CircularProgressIndicator(
            color = contentColorFor(MaterialTheme.colors.background)
        )
    }
}

@Preview
@Composable
fun LoadingMatchListScreenPreview() {
    CSTVTheme {
        MatchListScreen(
            isLoading = true,
            matches = emptyList()
        )
    }
}

@Preview
@Composable
fun MatchListScreenPreview() {
    CSTVTheme {
        MatchListScreen(
            isLoading = false,
            matches = MutableList(3) { index ->
                Match(
                    id = MatchId(index.toLong()),
                    name = "Match name",
                    status = MatchStatus.NotStarted,
                    scheduledAt = ZonedDateTime.now(),
                    opponents = Pair(
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
            }
        )
    }
}
