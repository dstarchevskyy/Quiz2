package com.droiddevstar.quiz.list

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.value.MutableValue
import com.arkivanov.decompose.value.Value
import com.droiddevstar.quiz.list.ListComponent

class DefaultListComponent(
    componentContext: ComponentContext,
    private val onItemSelected: (item: String) -> Unit,
    private val onLoad: () -> Unit
) : ListComponent {
    override val model: Value<ListComponent.Model> =
        MutableValue(
            ListComponent.Model(
            items = List(100
            ) { "Item $it" }))

    override fun onItemClicked(item: String) {
        onItemSelected(item)
    }

    override fun onLoadClicked() {
        println("@@@onLoadClicked()")
        onLoad()
    }
}