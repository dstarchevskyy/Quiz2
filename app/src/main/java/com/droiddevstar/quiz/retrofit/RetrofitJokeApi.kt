package com.droiddevstar.quiz.retrofit

import retrofit2.http.FieldMap
import retrofit2.http.GET
import retrofit2.http.POST

interface RetrofitJokeApi {

    @GET("/api?format=json")
//    @GET("/auth/login")
    suspend fun getJoke(): JokeModel
//    fun getJokeAsync(): Deferred<Response<ResponseBody>>

    @POST("/api?format=json&auth_token")
    suspend fun getAuthenticationToken(@FieldMap params: HashMap<String, String>): AuthTokenModel

}