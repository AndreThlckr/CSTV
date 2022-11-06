package io.github.andrethlckr.cstv.match.data.source.remote.service

import io.github.andrethlckr.cstv.BuildConfig
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

class PandaScoreTokenInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request: Request = chain.request()
            .newBuilder()
            .addHeader("Authorization", "Bearer ${BuildConfig.pandaScoreApiKey}")
            .build()

        return chain.proceed(request)
    }
}