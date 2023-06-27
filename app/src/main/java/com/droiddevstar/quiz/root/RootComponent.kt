package com.droiddevstar.quiz.root

import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.value.Value

interface RootComponent {

    val stack: Value<ChildStack<*, RootComponentChild>>

    fun onBackClicked(toIndex: Int)

}