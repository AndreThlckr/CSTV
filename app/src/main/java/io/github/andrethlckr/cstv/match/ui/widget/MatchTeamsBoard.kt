package io.github.andrethlckr.cstv.match.ui.widget

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import io.github.andrethlckr.cstv.R
import io.github.andrethlckr.cstv.core.ui.theme.CSTVTheme
import io.github.andrethlckr.cstv.match.domain.Team
import io.github.andrethlckr.cstv.match.domain.TeamId

@Composable
fun MatchTeamsBoard(
    first: Team?,
    second: Team?,
    modifier: Modifier = Modifier
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
        modifier = modifier.fillMaxWidth()
    ) {
        TeamEmblem(first)

        Text(
            text = stringResource(R.string.versus),
            color = MaterialTheme.colors.onSurface.copy(alpha = 0.5F),
            style = MaterialTheme.typography.body1,
            modifier = Modifier.padding(horizontal = 20.dp)
        )

        TeamEmblem(second)
    }
}

@Composable
fun TeamEmblem(
    team: Team?
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(team?.image?.url)
                .crossfade(true)
                .build(),
            placeholder = painterResource(R.drawable.circle_placeholder),
            error = painterResource(R.drawable.circle_placeholder),
            fallback = painterResource(R.drawable.circle_placeholder),
            contentDescription = stringResource(R.string.team_emblem_description),
            contentScale = ContentScale.Fit,
            modifier = Modifier
                .size(60.dp)
        )

        Text(
            text = team?.name.orEmpty(),
            color = MaterialTheme.colors.onSurface,
            style = MaterialTheme.typography.body2,
            modifier = Modifier.padding(top = 10.dp)
        )
    }
}

@Preview
@Composable
fun DisputeRowPreview() {
    CSTVTheme {
        MatchTeamsBoard(
            first = Team(
                id = TeamId(2),
                name = "First",
                image = null,
                players = null
            ),
            second = null
        )
    }
}

@Preview
@Composable
fun TeamEmblemPreview() {
    CSTVTheme {
        TeamEmblem(
            team = Team(
                id = TeamId(2),
                name = "First",
                image = null,
                players = null
            )
        )
    }
}
