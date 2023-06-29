package com.droiddevstar.quiz.feature_jokes_list

import com.arkivanov.decompose.value.Value

interface ListComponent {

    val model: Value<ListComponentModel>

    fun onItemClicked(item: String)

    fun onLoadClicked()

}