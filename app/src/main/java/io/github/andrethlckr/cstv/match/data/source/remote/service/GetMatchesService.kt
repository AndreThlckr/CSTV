package io.github.andrethlckr.cstv.match.data.source.remote.service

import io.github.andrethlckr.cstv.core.data.NetworkResult
import io.github.andrethlckr.cstv.match.data.source.remote.MatchResponse
import retrofit2.http.GET

interface GetMatchesService {

    @GET("/csgo/matches")
    suspend fun fetchMatches(): NetworkResult<List<MatchResponse>>
}
