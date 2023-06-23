package com.droiddevstar.quiz

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.value.MutableValue
import com.arkivanov.decompose.value.Value

class DefaultDetailsComponent(
    componentContext: ComponentContext,
    private val item: String
) : DetailsComponent {
    override val model: Value<DetailsComponent.Model>
        get() = MutableValue(DetailsComponent.Model(item))
}