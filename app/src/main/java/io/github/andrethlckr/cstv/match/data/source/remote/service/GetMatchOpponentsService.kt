package io.github.andrethlckr.cstv.match.data.source.remote.service

import io.github.andrethlckr.cstv.core.data.NetworkResult
import io.github.andrethlckr.cstv.match.data.source.remote.OpponentsResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface GetMatchOpponentsService {

    @GET("/matches/{match_id}/opponents")
    suspend fun fetchOpponents(
        @Path("match_id") matchId: Long
    ): NetworkResult<OpponentsResponse>
}
