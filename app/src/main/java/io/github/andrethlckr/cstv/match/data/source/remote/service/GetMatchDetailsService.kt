package io.github.andrethlckr.cstv.match.data.source.remote.service

import io.github.andrethlckr.cstv.core.data.NetworkResult
import io.github.andrethlckr.cstv.match.data.source.remote.TeamResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface GetMatchDetailsService {

    @GET("/csgo/teams")
    suspend fun fetchMatchDetails(
        @Query("filter[id][0]", encoded = true) firstTeamId: Long,
        @Query("filter[id][1]", encoded = true) secondTeamId: Long
    ): NetworkResult<List<TeamResponse>>
}
