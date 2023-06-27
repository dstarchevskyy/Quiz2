package com.droiddevstar.quiz.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

@InstallIn(SingletonComponent::class)
@Module
object RemoteModule {

    @Provides
    fun provideMoshi(): Moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

    @Provides
    fun provideOkHttp(): OkHttpClient {
        return  OkHttpClient.Builder()
            .connectTimeout(3.toLong(), TimeUnit.SECONDS)
            .readTimeout(3.toLong(), TimeUnit.SECONDS)
            .build()
    }

    @Provides
    fun getRetrofit(
        okHttpClient: OkHttpClient,
        moshi: Moshi,
    ): Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(
            MoshiConverterFactory.create(moshi))
        .client(okHttpClient)
        .build()

    @Provides
    fun provideRetrofitJokeApi(
        retrofit: Retrofit,
    ): RetrofitJokeApi {
        return retrofit.create(RetrofitJokeApi::class.java)
    }

    @Provides
    fun provideJokeApi(
        retrofitJokeApi: RetrofitJokeApi,
    ): JokeApi {
        return JokesApiImpl(retrofitJokeApi)
    }
}