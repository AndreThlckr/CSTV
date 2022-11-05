package io.github.andrethlckr.cstv.match.ui.list

import io.github.andrethlckr.cstv.match.domain.Match

data class MatchListState(
    val isLoading: Boolean,
    val matches: List<Match>
)
