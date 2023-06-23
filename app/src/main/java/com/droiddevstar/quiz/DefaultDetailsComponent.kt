package com.droiddevstar.quiz

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.value.MutableValue
import com.arkivanov.decompose.value.Value

class DefaultDetailsComponent(
    componentContext: ComponentContext
) : DetailsComponent {
    override val model: Value<DetailsComponent.Model>
        get() = MutableValue(DetailsComponent.Model("asdf"))
}