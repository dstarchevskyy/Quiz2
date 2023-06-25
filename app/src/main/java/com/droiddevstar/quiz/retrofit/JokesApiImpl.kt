package com.droiddevstar.quiz.retrofit

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class JokesApiImpl(private val retrofitJokeApi: RetrofitJokeApi) : JokeApi {
    override fun fetchJoke(): Flow<JokeModel> = flow {
        emit(retrofitJokeApi.getJoke())
    }
}

const val BASE_URL = "https://geek-jokes.sameerkumar.website/"
