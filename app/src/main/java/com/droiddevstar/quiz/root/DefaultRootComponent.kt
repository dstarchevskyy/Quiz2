package com.droiddevstar.quiz.root

import android.content.Context
import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.router.stack.popTo
import com.arkivanov.decompose.router.stack.push
import com.arkivanov.decompose.value.Value
import com.droiddevstar.quiz.details.DefaultDetailsComponent
import com.droiddevstar.quiz.details.DetailsComponent
import com.droiddevstar.quiz.domain.GetJokeInteractor
import com.droiddevstar.quiz.list.ListComponentImpl
import com.droiddevstar.quiz.list.ListComponent
import com.droiddevstar.quiz.main_screen.MainScreenComponent
import com.droiddevstar.quiz.main_screen.MainScreenComponentImpl
import com.droiddevstar.quiz.repository.JokesRepository
import com.droiddevstar.quiz.tutorial.TutorialComponent
import com.droiddevstar.quiz.tutorial.TutorialComponentImpl
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

class DefaultRootComponent @Inject constructor(
    val componentContext: ComponentContext,
    val appContext: Context,
    val jokesRepository: JokesRepository
) : RootComponent, ComponentContext by componentContext {

    private val navigation = StackNavigation<Config>()

    override val stack: Value<ChildStack<*, RootComponentChild>> =
        childStack(
            source = navigation,
            initialConfiguration = Config.List, // The initial child component is List
            handleBackButton = true, // Automatically pop from the stack on back button presses
            childFactory = ::child,
        )

    init {
        println("DefaultRootComponent")
    }

    private fun child(config: Config, componentContext: ComponentContext): RootComponentChild =
        when (config) {
            is Config.MainScreen -> RootComponentChild.MainChild(mainComponent(componentContext))
            is Config.Tutorial -> RootComponentChild.TutorialChild(tutorialComponent(componentContext))
            is Config.List -> RootComponentChild.ListChild(listComponent(componentContext))
            is Config.Details -> RootComponentChild.DetailsChild(
                detailsComponent(
                    componentContext,
                    config
                )
            )
        }

    private fun mainComponent(
        componentContext: ComponentContext
    ): MainScreenComponent = MainScreenComponentImpl(
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
        ListComponentImpl(
            componentContext = componentContext,
            mainContext = Dispatchers.IO,
            getJoke = GetJokeInteractor(
                repository = jokesRepository
            ),
//            jokesRepository = jokesRepository,
            onItemSelected = { item: String -> // Supply dependencies and callbacks
                navigation.push(Config.Details(item = item)) // Push the details component
            },
        )

    private fun detailsComponent(componentContext: ComponentContext, config: Config.Details): DetailsComponent =
        DefaultDetailsComponent(
            componentContext = componentContext,
            item = config.item, // Supply arguments from the configuration
        )

    override fun onBackClicked(toIndex: Int) {
        navigation.popTo(index = toIndex)
    }

}