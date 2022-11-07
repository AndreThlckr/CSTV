package io.github.andrethlckr.cstv.match.data.source.remote.service

import io.github.andrethlckr.cstv.core.data.NetworkResult
import io.github.andrethlckr.cstv.match.data.source.remote.MatchResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface GetMatchDetailsService {

    @GET("/csgo/matches/{matchId}")
    suspend fun fetchMatchDetails(
        @Path("matchId") matchId: Long
    ): NetworkResult<MatchResponse>
}
