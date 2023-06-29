package com.droiddevstar.quiz.root

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.arkivanov.decompose.extensions.compose.jetpack.stack.Children
import com.arkivanov.decompose.extensions.compose.jetpack.stack.animation.fade
import com.arkivanov.decompose.extensions.compose.jetpack.stack.animation.plus
import com.arkivanov.decompose.extensions.compose.jetpack.stack.animation.scale
import com.arkivanov.decompose.extensions.compose.jetpack.stack.animation.stackAnimation
import com.droiddevstar.quiz.details.DetailsContent
import com.droiddevstar.quiz.feature_jokes_list.ListContent
import com.droiddevstar.quiz.main_screen.MainScreen
import com.droiddevstar.quiz.tutorial.TutorialScreen

@Composable
fun RootContent(
    component: RootComponent,
    modifier: Modifier = Modifier
) {
    Children(
        stack = component.stack,
        modifier = modifier,
        animation = stackAnimation(fade() + scale()),
    ) {
        when (val child = it.instance) {
            is RootComponentChild.MainChild -> MainScreen(component = child.component)
            is RootComponentChild.TutorialChild -> TutorialScreen()
            is RootComponentChild.ListChild -> com.droiddevstar.quiz.feature_jokes_list.ListContent(
                component = child.component
            )
            is RootComponentChild.DetailsChild -> DetailsContent(component = child.component, modifier)
        }
    }
}