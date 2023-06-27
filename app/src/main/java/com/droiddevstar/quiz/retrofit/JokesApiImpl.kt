package com.droiddevstar.quiz.retrofit

import com.droiddevstar.quiz.coreapi.JokeModel
import com.droiddevstar.quiz.network.JokeApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class JokesApiImpl(private val retrofitJokeApi: RetrofitJokeApi) : JokeApi {
    override fun fetchJoke(): Flow<JokeModel> = flow {
        emit(retrofitJokeApi.getJoke())
    }
}

const val BASE_URL = "https://geek-jokes.sameerkumar.website/"
