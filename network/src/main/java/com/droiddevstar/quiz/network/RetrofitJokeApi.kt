package com.droiddevstar.quiz.network

import com.droiddevstar.quiz.coreapi.JokeModel
import retrofit2.http.GET

interface RetrofitJokeApi {

    @GET("/api?format=json")
    suspend fun getJoke(): JokeModel

}