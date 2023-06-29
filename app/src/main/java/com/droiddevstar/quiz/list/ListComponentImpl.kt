package com.droiddevstar.quiz.list

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.value.MutableValue
import com.arkivanov.decompose.value.Value
import com.arkivanov.essenty.lifecycle.Lifecycle
import com.arkivanov.essenty.lifecycle.LifecycleOwner
import com.arkivanov.essenty.lifecycle.doOnDestroy
import com.droiddevstar.quiz.coreapi.JokeModel
import com.droiddevstar.quiz.database.JokeDBModel
import com.droiddevstar.quiz.domain.GetJokeInteractor
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class ListComponentImpl(
    private val componentContext: ComponentContext,
    mainContext: CoroutineContext,
//    private val jokesRepository: JokesRepository,
    private val getJoke: GetJokeInteractor,
    private val onItemSelected: (item: String) -> Unit,
) : ListComponent, ComponentContext by componentContext
{
    // The scope is automatically cancelled when the component is destroyed
    private val scope = coroutineScope(mainContext + SupervisorJob())

    private val jokeState: MutableState<JokeModel> = mutableStateOf(JokeModel(""))
    private val allJokesState: SnapshotStateList<JokeDBModel> = mutableStateListOf()

    override val model: Value<ListComponentModel> =
        MutableValue(
            ListComponentModel(
                items = List(100) { "Item $it" },
                stateDate = jokeState,
                allJokesState = allJokesState
            )
        )

    override fun onItemClicked(item: String) {
        onItemSelected(item)
    }

    override fun onLoadClicked() {
        val jokeFlow: Flow<JokeModel> = getJoke()

        scope.launch {
            jokeFlow.collectLatest {
                jokeState.value = it
            }

//            allJokesState.addAll(jokesRepository.getAllJokes())
        }
    }
}

fun CoroutineScope(
    context: CoroutineContext,
    lifecycle: Lifecycle
): CoroutineScope {
    val scope = CoroutineScope(context)
    lifecycle.doOnDestroy(scope::cancel)
    return scope
}

fun LifecycleOwner.coroutineScope(
    context: CoroutineContext
): CoroutineScope {
    return CoroutineScope(context, lifecycle)
}
