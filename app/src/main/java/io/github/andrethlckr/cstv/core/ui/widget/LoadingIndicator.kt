package io.github.andrethlckr.cstv.core.ui.widget

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import io.github.andrethlckr.cstv.core.ui.theme.CSTVTheme

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
fun LoadingIndicatorPreview() {
    CSTVTheme {
        LoadingIndicator()
    }
}
