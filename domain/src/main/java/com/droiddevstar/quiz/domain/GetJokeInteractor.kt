package com.droiddevstar.quiz.domain

import com.droiddevstar.quiz.coreapi.JokeModel
import com.droiddevstar.quiz.repository.JokesRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetJokeInteractor @Inject constructor(
    private val jokesRepository: JokesRepository
) {

    operator fun invoke(): Flow<JokeModel> {
        return jokesRepository.getJoke()
    }
}