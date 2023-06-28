package com.droiddevstar.quiz.repository

import com.droiddevstar.quiz.coreapi.JokeModel
import com.droiddevstar.quiz.database.JokeDBModel
import kotlinx.coroutines.flow.Flow

interface JokesRepository {
    fun getJoke(): Flow<JokeModel>

    suspend fun getAllJokes(): List<JokeDBModel>
}