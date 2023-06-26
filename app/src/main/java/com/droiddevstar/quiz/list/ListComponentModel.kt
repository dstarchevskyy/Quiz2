package com.droiddevstar.quiz.list

import androidx.compose.runtime.MutableState
import com.droiddevstar.quiz.retrofit.JokeModel

data class ListComponentModel(
    val items: List<String>,
    val stateDate: MutableState<JokeModel>
)