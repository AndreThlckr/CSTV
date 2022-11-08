package io.github.andrethlckr.cstv.di

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.github.andrethlckr.cstv.core.data.NetworkResultCallAdapterFactory
import io.github.andrethlckr.cstv.match.data.MatchRepositoryImpl
import io.github.andrethlckr.cstv.match.data.source.remote.service.GetMatchesService
import io.github.andrethlckr.cstv.match.data.source.remote.service.GetTeamsService
import io.github.andrethlckr.cstv.match.data.source.remote.service.PandaScoreTokenInterceptor
import io.github.andrethlckr.cstv.match.domain.MatchRepository
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideOkHttpClient() = OkHttpClient
        .Builder()
        .addInterceptor(PandaScoreTokenInterceptor())
        .build()

    @OptIn(ExperimentalSerializationApi::class)
    @Singleton
    @Provides
    fun provideRetrofit(
        okHttpClient: OkHttpClient
    ): Retrofit {
        val converterFactory = jsonConfiguration.asConverterFactory("application/json".toMediaType())

        return Retrofit.Builder()
            .client(okHttpClient)
            .addConverterFactory(converterFactory)
            .addCallAdapterFactory(NetworkResultCallAdapterFactory())
            .baseUrl("https://api.pandascore.co")
            .build()
    }

    @Singleton
    @Provides
    fun provideGetMatchesService(retrofit: Retrofit): GetMatchesService = retrofit.create(GetMatchesService::class.java)

    @Singleton
    @Provides
    fun provideGetTeamsService(retrofit: Retrofit): GetTeamsService = retrofit.create(GetTeamsService::class.java)
}

private val jsonConfiguration = Json {
    ignoreUnknownKeys = true
}

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindMatchRepository(
        matchRepositoryImpl: MatchRepositoryImpl
    ): MatchRepository
}
