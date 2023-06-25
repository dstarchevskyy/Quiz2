package com.droiddevstar.quiz.list

import com.arkivanov.decompose.value.Value

interface ListComponent {
    val model: Value<Model>

    fun onItemClicked(item: String)

    fun onLoadClicked()

    data class Model(
        val items: List<String>,
    )
}