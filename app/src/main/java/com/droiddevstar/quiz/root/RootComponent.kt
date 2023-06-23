package com.droiddevstar.quiz.root

import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.value.Value
import com.droiddevstar.quiz.details.DetailsComponent
import com.droiddevstar.quiz.list.ListComponent
import com.droiddevstar.quiz.main_screen.MainScreenComponent
import com.droiddevstar.quiz.tutorial.TutorialComponent

interface RootComponent {

    val stack: Value<ChildStack<*, Child>>

    fun onBackClicked(toIndex: Int)


    sealed class Child {
        class MainChild(val component: MainScreenComponent): Child()
        class TutorialChild(val component: TutorialComponent): Child()
        class ListChild(val component: ListComponent) : Child()
        class DetailsChild(val component: DetailsComponent) : Child()
    }
}