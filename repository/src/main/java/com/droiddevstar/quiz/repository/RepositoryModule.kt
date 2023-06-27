package com.droiddevstar.quiz.repository

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {

    @Binds
    fun bindJokesRepository(
        jokesRepository: JokesRepositoryImpl
    ): JokesRepository

}