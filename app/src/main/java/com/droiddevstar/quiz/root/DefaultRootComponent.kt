package com.droiddevstar.quiz.root

import android.annotation.SuppressLint
import android.content.Context
import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.router.stack.popTo
import com.arkivanov.decompose.router.stack.push
import com.arkivanov.decompose.value.Value
import com.arkivanov.essenty.parcelable.Parcelable
import com.droiddevstar.quiz.details.DefaultDetailsComponent
import com.droiddevstar.quiz.list.DefaultListComponent
import com.droiddevstar.quiz.details.DetailsComponent
import com.droiddevstar.quiz.list.ListComponent
import com.droiddevstar.quiz.main_screen.MainScreenComponent
import com.droiddevstar.quiz.main_screen.MainScreenComponentImpl
import com.droiddevstar.quiz.tutorial.TutorialComponent
import com.droiddevstar.quiz.tutorial.TutorialComponentImpl
import kotlinx.parcelize.Parcelize

class DefaultRootComponent(
    componentContext: ComponentContext,
    private val appContext: Context
) : RootComponent, ComponentContext by componentContext {

    private val navigation = StackNavigation<Config>()

    override val stack: Value<ChildStack<*, RootComponent.Child>> =
        childStack(
            source = navigation,
            initialConfiguration = Config.MainScreen, // The initial child component is List
            handleBackButton = true, // Automatically pop from the stack on back button presses
            childFactory = ::child,
        )

    init {
        println("DefaultRootComponent")
    }

    private fun child(config: Config, componentContext: ComponentContext): RootComponent.Child =
        when (config) {
            is Config.MainScreen -> RootComponent.Child.MainChild(mainComponent(componentContext))
            is Config.Tutorial -> RootComponent.Child.TutorialChild(tutorialComponent(componentContext))
            is Config.List -> RootComponent.Child.ListChild(listComponent(componentContext))
            is Config.Details -> RootComponent.Child.DetailsChild(
                detailsComponent(
                    componentContext,
                    config
                )
            )
        }

    private fun mainComponent(componentContext: ComponentContext): MainScreenComponent = MainScreenComponentImpl(
        onButtonClicked = { navItem ->
            navigation.push(Config.Tutorial)
        }
    )

    private fun tutorialComponent(
        componentContext: ComponentContext
    ): TutorialComponent = TutorialComponentImpl(
        componentContext = componentContext,
        appContext = appContext
    )
    private fun listComponent(componentContext: ComponentContext): ListComponent =
        DefaultListComponent(
            componentContext = componentContext,
            onItemSelected = { item: String -> // Supply dependencies and callbacks
                navigation.push(Config.Details(item = item)) // Push the details component
            },
        )

    private fun detailsComponent(componentContext: ComponentContext, config: Config.Details): DetailsComponent =
        DefaultDetailsComponent(
            componentContext = componentContext,
            item = config.item, // Supply arguments from the configuration
//            onFinished = navigation::pop, // Pop the details component
        )

    override fun onBackClicked(toIndex: Int) {
        navigation.popTo(index = toIndex)
    }

    @Parcelize // The `kotlin-parcelize` plugin must be applied if you are targeting Android
    private sealed interface Config : Parcelable {

        @SuppressLint("ParcelCreator")
        object Tutorial : Config
        @SuppressLint("ParcelCreator")
        object MainScreen : Config

        @SuppressLint("ParcelCreator")
        object List : Config

        @SuppressLint("ParcelCreator")
        data class Details(val item: String) : Config
    }
}