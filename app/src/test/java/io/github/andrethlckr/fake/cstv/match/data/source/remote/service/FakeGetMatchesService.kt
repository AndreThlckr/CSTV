package io.github.andrethlckr.fake.cstv.match.data.source.remote.service

import io.github.andrethlckr.cstv.core.data.NetworkResult
import io.github.andrethlckr.cstv.match.data.source.remote.MatchResponse
import io.github.andrethlckr.cstv.match.data.source.remote.service.GetMatchesService

class FakeGetMatchesService(
    var response: List<MatchResponse>? = null
) : GetMatchesService {

    override suspend fun fetchUpcomingMatches() = response?.let { NetworkResult.Success(it) } ?: NetworkResult.Failure
}
