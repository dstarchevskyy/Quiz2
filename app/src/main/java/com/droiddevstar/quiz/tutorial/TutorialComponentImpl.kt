package com.droiddevstar.quiz.tutorial

import com.arkivanov.decompose.ComponentContext

class TutorialComponentImpl(
    componentContext: ComponentContext
) : TutorialComponent, ComponentContext by componentContext {
}