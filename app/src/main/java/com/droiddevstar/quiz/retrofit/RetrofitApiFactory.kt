package com.droiddevstar.quiz.retrofit

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitApiFactory {
    private fun createMoshi(): Moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

    private fun getOkHttp(): OkHttpClient {
        return  OkHttpClient.Builder()
            .connectTimeout(3.toLong(), TimeUnit.SECONDS)
            .readTimeout(3.toLong(), TimeUnit.SECONDS)
        .build()
    }

    private fun getRetrofit(): Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(MoshiConverterFactory.create(createMoshi()))
        .client(getOkHttp())
        .build()

    val jokeApiService: RetrofitJokeApi = getRetrofit()
        .create(RetrofitJokeApi::class.java)

}