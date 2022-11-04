package io.github.andrethlckr.cstv.match.domain

import io.github.andrethlckr.cstv.core.data.NetworkResult

interface MatchRepository {

    fun getMatches(): NetworkResult<List<Match>>
}
