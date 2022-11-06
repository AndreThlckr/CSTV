package io.github.andrethlckr.cstv.di

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.github.andrethlckr.cstv.core.data.NetworkResultCallAdapterFactory
import io.github.andrethlckr.cstv.match.data.source.remote.service.GetMatchesService
import io.github.andrethlckr.cstv.match.data.source.remote.service.PandaScoreTokenInterceptor
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType
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
        val converterFactory = Json.asConverterFactory(MediaType.get("application/json"))

        return Retrofit.Builder()
            .client(okHttpClient)
            .addConverterFactory(converterFactory)
            .addCallAdapterFactory(NetworkResultCallAdapterFactory())
            .baseUrl("https://api.pandascore.co")
            .build()
    }

    @Singleton
    @Provides
    fun provideGetMatchesService(retrofit: Retrofit) = retrofit.create(GetMatchesService::class.java)
}