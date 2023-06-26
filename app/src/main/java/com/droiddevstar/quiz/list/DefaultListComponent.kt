package com.droiddevstar.quiz.list

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.value.MutableValue
import com.arkivanov.decompose.value.Value
import com.arkivanov.essenty.lifecycle.Lifecycle
import com.arkivanov.essenty.lifecycle.LifecycleOwner
import com.arkivanov.essenty.lifecycle.doOnDestroy
import com.droiddevstar.quiz.list.ListComponent
import com.droiddevstar.quiz.retrofit.JokeApi
import com.droiddevstar.quiz.retrofit.JokeModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.coroutineContext

class DefaultListComponent(
    private val componentContext: ComponentContext,
    mainContext: CoroutineContext,
    private val jokeApi: JokeApi,
    private val onItemSelected: (item: String) -> Unit,
    private val onLoad: () -> Unit
) : ListComponent, ComponentContext by componentContext {
    // The scope is automatically cancelled when the component is destroyed
    private val scope = coroutineScope(mainContext + SupervisorJob())

    private val jokeState: MutableState<JokeModel> = mutableStateOf<JokeModel>(JokeModel(""))

    override val model: Value<ListComponentModel> =
        MutableValue(
            ListComponentModel(
                items = List(100) { "Item $it" },
                stateDate = jokeState)
        )

    override fun onItemClicked(item: String) {
        onItemSelected(item)
    }

    override fun onLoadClicked() {
        println("@@@onLoadClicked()")
        val jokeFlow: Flow<JokeModel> = jokeApi.fetchJoke()

        scope.launch {
            jokeFlow.collectLatest {
                println("@@@DefaultListComponent:collectLatest: $it ")
                jokeState.value = it
            }
        }
    }
}

fun CoroutineScope(context: CoroutineContext, lifecycle: Lifecycle): CoroutineScope {
    val scope = CoroutineScope(context)
    lifecycle.doOnDestroy(scope::cancel)
    return scope
}

fun LifecycleOwner.coroutineScope(context: CoroutineContext): CoroutineScope =
    CoroutineScope(context, lifecycle)
