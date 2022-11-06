package io.github.andrethlckr.cstv.core.ui.date

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.res.stringResource
import io.github.andrethlckr.cstv.R

@Composable
fun rememberDateToTextFormatter(): DateToTextFormatter {
    val todayString =  stringResource(R.string.today)

    return remember(todayString) {
        DateToTextFormatter(todayString = todayString)
    }
}
