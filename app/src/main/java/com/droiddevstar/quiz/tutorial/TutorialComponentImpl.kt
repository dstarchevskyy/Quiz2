package com.droiddevstar.quiz.tutorial

import android.content.Context
import com.arkivanov.decompose.ComponentContext

class TutorialComponentImpl(
    componentContext: ComponentContext,
    val appContext: Context
) : TutorialComponent, ComponentContext by componentContext {

    init {
        CsvReader(appContext).readCsv().forEach {
            println("@@@${it.name}")
        }
    }
}