package io.github.andrethlckr.cstv.match.data

import io.github.andrethlckr.cstv.core.data.NetworkResult
import io.github.andrethlckr.cstv.match.domain.Match
import io.github.andrethlckr.cstv.match.domain.MatchRepository
import javax.inject.Inject

class MatchRepositoryImpl @Inject constructor(): MatchRepository {

    override suspend fun getMatches(): NetworkResult<List<Match>> = NetworkResult.Success(emptyList())
}
