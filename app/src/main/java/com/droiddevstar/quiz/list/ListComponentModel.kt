package com.droiddevstar.quiz.list

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.snapshots.SnapshotStateList
import com.droiddevstar.quiz.coreapi.JokeModel
import com.droiddevstar.quiz.database.JokeDBModel

data class ListComponentModel(
    val items: List<String>,
    val stateDate: MutableState<JokeModel>,
    val allJokesState: SnapshotStateList<JokeDBModel>
)