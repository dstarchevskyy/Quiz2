package com.droiddevstar.quiz

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.droiddevstar.quiz.root.RootComponent
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
    lateinit var rootComponent: RootComponent

    private lateinit var job: Job

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        job = Job()

        setContent {
            QuizTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    RootContent(
                        component = rootComponent,
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }
        }
    }

    override val coroutineContext: CoroutineContext
        get() =  Dispatchers.Main + job

}

@Preview(showBackground = true)
@Composable
fun PreviewListContent() {
    Text(text = "ASDF")
}
