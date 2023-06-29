package com.droiddevstar.quiz.root

import com.droiddevstar.quiz.details.DetailsComponent
import com.droiddevstar.quiz.feature_jokes_list.ListComponent
import com.droiddevstar.quiz.main_screen.MainScreenComponent
import com.droiddevstar.quiz.tutorial.TutorialComponent

sealed class RootComponentChild {
    class MainChild(val component: MainScreenComponent): RootComponentChild()
    class TutorialChild(val component: TutorialComponent): RootComponentChild()
    class ListChild(val component: com.droiddevstar.quiz.feature_jokes_list.ListComponent) : RootComponentChild()
    class DetailsChild(val component: DetailsComponent) : RootComponentChild()
}