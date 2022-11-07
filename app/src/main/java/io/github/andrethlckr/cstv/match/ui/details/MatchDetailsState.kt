package io.github.andrethlckr.cstv.match.ui.details

import io.github.andrethlckr.cstv.match.domain.Match

data class MatchDetailsState(
    val isLoading: Boolean,
    val match: Match?
)
