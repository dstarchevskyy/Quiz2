package com.droiddevstar.quiz.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.droiddevstar.quiz.database.JokeDBModel.Companion.JOKES_TABLE_NAME

@Entity(tableName = JOKES_TABLE_NAME)
data class JokeDBModel(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,

    val joke: String,
) {
    companion object {
        const val JOKES_TABLE_NAME = "jokes"
    }
}