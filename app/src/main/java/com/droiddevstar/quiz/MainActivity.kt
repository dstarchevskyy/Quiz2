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
import com.droiddevstar.quiz.root.DefaultRootComponent
import com.droiddevstar.quiz.root.RootContent
import com.droiddevstar.quiz.ui.theme.QuizTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlin.coroutines.CoroutineContext

@AndroidEntryPoint
class MainActivity : ComponentActivity(), CoroutineScope {
    private lateinit var job: Job

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        job = Job()

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
                }
            }
        }
    }

    override val coroutineContext: CoroutineContext
        get() =  Dispatchers.Main + job

}
