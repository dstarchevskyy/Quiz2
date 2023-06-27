package com.droiddevstar.quiz.retrofit

import com.droiddevstar.quiz.coreapi.JokeModel
import com.droiddevstar.quiz.network.JokeApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn

class JokesRepositoryImpl(private val jokeApi: JokeApi): JokesRepository {
    override fun getJoke(): Flow<JokeModel> =
        jokeApi.fetchJoke().flowOn(Dispatchers.IO)
}