package io.github.andrethlckr.cstv.match.data.source.remote.service

import io.github.andrethlckr.cstv.core.data.NetworkResult
import io.github.andrethlckr.cstv.match.data.source.remote.TeamResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface GetTeamsService {

    @GET("/csgo/teams")
    suspend fun fetchTeams(
        @Query("filter[id]", encoded = true) teamId: Long
    ): NetworkResult<List<TeamResponse>>
}
