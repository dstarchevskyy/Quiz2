package com.droiddevstar.quiz.list

import com.arkivanov.decompose.value.Value

interface ListComponent {

    val model: Value<ListComponentModel>

    fun onItemClicked(item: String)

    fun onLoadClicked()

}