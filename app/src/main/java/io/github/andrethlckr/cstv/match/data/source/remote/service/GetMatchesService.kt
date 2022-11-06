package io.github.andrethlckr.cstv.match.data.source.remote.service

import io.github.andrethlckr.cstv.core.data.NetworkResult
import io.github.andrethlckr.cstv.match.data.source.remote.MatchResponse
import retrofit2.http.GET

interface GetMatchesService {

    @GET("/csgo/matches/upcoming?sort=begin_at&sort=scheduled_at")
    suspend fun fetchUpcomingMatches(): NetworkResult<List<MatchResponse>>
}
