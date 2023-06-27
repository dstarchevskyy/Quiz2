package com.droiddevstar.quiz.retrofit

import com.droiddevstar.quiz.coreapi.JokeModel
import kotlinx.coroutines.flow.Flow

interface JokesRepository {
    fun getJoke(): Flow<JokeModel>
}