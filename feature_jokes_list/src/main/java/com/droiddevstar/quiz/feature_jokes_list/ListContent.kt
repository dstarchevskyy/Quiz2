package com.droiddevstar.quiz.feature_jokes_list

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.arkivanov.decompose.extensions.compose.jetpack.subscribeAsState
import com.arkivanov.decompose.value.MutableValue
import com.arkivanov.decompose.value.Value
import com.droiddevstar.quiz.coreapi.JokeModel

@Composable
fun ListContent(
    component: ListComponent,
) {
    val model: State<ListComponentModel> = component.model.subscribeAsState()

    Column(Modifier.fillMaxSize()) {

        Button(
            onClick = { component.onLoadClicked() },
            modifier = Modifier.padding(16.dp)
        ) {
            Text(text = stringResource(id = R.string.load_next_joke))
        }

        Card(modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
        ) {
            val jokeText: String = model.value.stateData.value.joke
            if (jokeText.isNotEmpty()) {
                Text(
                    text = "${stringResource(id = R.string.new_joke)}: ${model.value.stateData.value.joke}",
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                )
            }
        }

        LazyColumn {
            items(model.value.allJokesState) { item ->
                Text(
                    text = item.joke,
                    modifier = Modifier
                        .padding(16.dp)
                        .clickable {
                            component.onItemClicked(item = item.joke)
                        })
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewListContent() {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        ListContent(
            component = object : ListComponent {
                @SuppressLint("UnrememberedMutableState")
                override val model: Value<ListComponentModel> = MutableValue(
                    ListComponentModel(
                        items = List(100) {
                            "Item $it"
                        },
                        stateData = mutableStateOf<JokeModel>(JokeModel("")),
                        allJokesState = mutableStateListOf()
                    )
                )

                override fun onItemClicked(item: String) {}

                override fun onLoadClicked() {}

            },
        )
    }
}