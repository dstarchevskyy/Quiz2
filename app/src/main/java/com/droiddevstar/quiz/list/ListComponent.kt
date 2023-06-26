package com.droiddevstar.quiz.list

import androidx.compose.runtime.MutableState
import com.arkivanov.decompose.value.Value
import com.droiddevstar.quiz.retrofit.JokeModel

interface ListComponent {
    val model: Value<Model>

    fun onItemClicked(item: String)

    fun onLoadClicked()

    data class Model(
        val items: List<String>,
        val stateDate: MutableState<JokeModel>
    )
}