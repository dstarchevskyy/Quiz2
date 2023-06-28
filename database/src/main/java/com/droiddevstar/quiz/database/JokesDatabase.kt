package com.droiddevstar.quiz.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    version = 1,
    entities = [JokeDBModel::class]
)
abstract class JokesDatabase : RoomDatabase() {

    abstract fun getJokesDao(): JokesDao
}