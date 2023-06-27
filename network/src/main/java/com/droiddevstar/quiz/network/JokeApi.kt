package com.droiddevstar.quiz.network

import com.droiddevstar.quiz.coreapi.JokeModel
import kotlinx.coroutines.flow.Flow

interface JokeApi {

    fun fetchJoke(): Flow<JokeModel>
}