package com.droiddevstar.quiz.database

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    fun provideJokesDatabase(
        @ApplicationContext context: Context
    ): JokesDatabase {
        return Room
            .databaseBuilder(context, JokesDatabase::class.java, JOKES_DATABASE_NAME)
            .build()
    }

    @Provides
    fun provideJokesDao(
        jokesDatabase: JokesDatabase
    ): JokesDao {
        return jokesDatabase.getJokesDao()
    }

}

const val JOKES_DATABASE_NAME = "jokes.db"