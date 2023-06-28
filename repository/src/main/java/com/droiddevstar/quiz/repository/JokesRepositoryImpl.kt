package com.droiddevstar.quiz.repository

import com.droiddevstar.quiz.coreapi.JokeModel
import com.droiddevstar.quiz.database.JokeDBModel
import com.droiddevstar.quiz.database.JokesDao
import com.droiddevstar.quiz.network.JokeApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.withContext
import javax.inject.Inject

class JokesRepositoryImpl @Inject constructor(
    private val jokeApi: JokeApi,
    private val jokesDao: JokesDao
): JokesRepository {

    override fun getJoke(): Flow<JokeModel> {
        return jokeApi.fetchJoke()
            .onEach {
                println("@@@insert: ${it.joke}")
                jokesDao.insertJoke(JokeDBModel(joke = it.joke))
            }
            .flowOn(Dispatchers.IO)
    }

    override suspend fun getAllJokes(): List<JokeDBModel> {
        return withContext(Dispatchers.IO) {
            println("@@@jokesDao: $jokesDao")
            return@withContext jokesDao.getAllJokes()
        }
    }
}