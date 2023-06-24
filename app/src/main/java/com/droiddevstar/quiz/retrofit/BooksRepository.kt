package com.droiddevstar.quiz.retrofit

import kotlinx.coroutines.flow.Flow

interface JokesRepository {
    fun getJoke(): Flow<JokeModel>
}