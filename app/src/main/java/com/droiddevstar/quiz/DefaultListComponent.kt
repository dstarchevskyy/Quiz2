package com.droiddevstar.quiz

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.value.MutableValue
import com.arkivanov.decompose.value.Value

class DefaultListComponent(
    componentContext: ComponentContext,
    private val onItemSelected: (item: String) -> Unit,
) : ListComponent {
    override val model: Value<ListComponent.Model> =
        MutableValue(ListComponent.Model(
            items = List(100
            ) { "Item $it" }))

    override fun onItemClicked(item: String) {
        onItemSelected(item)
    }
}