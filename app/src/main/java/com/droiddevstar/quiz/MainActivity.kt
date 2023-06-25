package com.droiddevstar.quiz

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.lifecycleScope
import com.arkivanov.decompose.defaultComponentContext
import com.droiddevstar.quiz.retrofit.JokeApi
import com.droiddevstar.quiz.retrofit.JokesApiImpl
import com.droiddevstar.quiz.retrofit.JokesRepositoryImpl
import com.droiddevstar.quiz.retrofit.RetrofitApiFactory
import com.droiddevstar.quiz.retrofit.RetrofitJokeApi
import com.droiddevstar.quiz.root.DefaultRootComponent
import com.droiddevstar.quiz.root.RootContent
import com.droiddevstar.quiz.ui.theme.QuizTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.coroutines.CoroutineContext

class MainActivity() : ComponentActivity(), CoroutineScope {
    private lateinit var job: Job

    lateinit var jokeApi: JokeApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        job = Job()

        val retrofitJokeApi = RetrofitApiFactory.jokeApiService
        jokeApi = JokesApiImpl(retrofitJokeApi)

        // Always create the root component outside Compose on the main thread
        val root =
            DefaultRootComponent(
                componentContext = defaultComponentContext(),
                appContext = applicationContext
            )

        setContent {
            QuizTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    RootContent(component = root, modifier = Modifier.fillMaxWidth())

                    Button(onClick = { getJoke() }) {
                        Text(text = "My text")
                    }
                }
            }
        }
    }

    override val coroutineContext: CoroutineContext
        get() =  Dispatchers.Main + job

    private fun getJoke() {
        launch {
            JokesRepositoryImpl(jokeApi).getJoke()
                .catch {
                    println("@@@catch: $it")
                }
                .collect {
                    println("@@@collect: $it")
                }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    QuizTheme {
        Greeting("Android")
    }
}