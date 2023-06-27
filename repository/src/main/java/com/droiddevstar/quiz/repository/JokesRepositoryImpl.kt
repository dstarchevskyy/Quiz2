package com.droiddevstar.quiz.repository

import com.droiddevstar.quiz.coreapi.JokeModel
import com.droiddevstar.quiz.network.JokeApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class JokesRepositoryImpl @Inject constructor(
    private val jokeApi: JokeApi
): JokesRepository {

    override fun getJoke(): Flow<JokeModel> {
        return jokeApi.fetchJoke().flowOn(Dispatchers.IO)
    }
}