package com.droiddevstar.quiz.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.droiddevstar.quiz.database.JokeDBModel.Companion.JOKES_TABLE_NAME

@Dao
interface JokesDao {

    @Insert(entity = JokeDBModel::class)
    fun insertJoke(jokeDBModel: JokeDBModel)


    @Query("SELECT * FROM $JOKES_TABLE_NAME")
    fun getAllJokes(): List<JokeDBModel>


}