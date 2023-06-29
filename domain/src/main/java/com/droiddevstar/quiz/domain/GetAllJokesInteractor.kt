package com.droiddevstar.quiz.domain

import com.droiddevstar.quiz.database.JokeDBModel
import com.droiddevstar.quiz.repository.JokesRepository
import javax.inject.Inject

class GetAllJokesInteractor @Inject constructor(
    private val jokesRepository: JokesRepository
) {

    suspend operator fun invoke(): List<JokeDBModel> {
        return jokesRepository.getAllJokes()
    }
}