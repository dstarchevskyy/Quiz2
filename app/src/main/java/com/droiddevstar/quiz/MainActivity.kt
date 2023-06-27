package com.droiddevstar.quiz

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.arkivanov.decompose.defaultComponentContext
import com.droiddevstar.quiz.network.JokeApi
import com.droiddevstar.quiz.network.JokesApiImpl
import com.droiddevstar.quiz.root.DefaultRootComponent
import com.droiddevstar.quiz.root.RootContent
import com.droiddevstar.quiz.ui.theme.QuizTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

@AndroidEntryPoint
class MainActivity : ComponentActivity(), CoroutineScope {

    @Inject
    lateinit var jokeApi: JokeApi

    private lateinit var job: Job

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        println("@@@jokeApi: $jokeApi")

        job = Job()

        // Always create the root component outside Compose on the main thread
        val root =
            DefaultRootComponent(
                componentContext = defaultComponentContext(),
                appContext = applicationContext,
                jokeApi = jokeApi
            )

        setContent {
            QuizTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    RootContent(component = root, modifier = Modifier.fillMaxWidth())
                }
            }
        }
    }

    override val coroutineContext: CoroutineContext
        get() =  Dispatchers.Main + job

}
