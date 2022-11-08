package io.github.andrethlckr.cstv.match.data.source.remote.service

import io.github.andrethlckr.cstv.core.data.NetworkResult
import io.github.andrethlckr.cstv.match.data.source.remote.MatchResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface GetMatchesService {

    @GET("/csgo/matches/upcoming")
    suspend fun fetchUpcomingMatches(
        @Query("filter[id][0]", encoded = true) matchId: Long? = null
    ): NetworkResult<List<MatchResponse>>
}
