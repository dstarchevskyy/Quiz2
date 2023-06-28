package com.droiddevstar.quiz.list

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.arkivanov.decompose.extensions.compose.jetpack.subscribeAsState
import com.arkivanov.decompose.value.MutableValue
import com.arkivanov.decompose.value.Value
import com.droiddevstar.quiz.coreapi.JokeModel

@Composable
fun ListContent(
    component: ListComponent,
    modifier: Modifier = Modifier
) {
    val model: State<ListComponentModel> = component.model.subscribeAsState()

    Column(Modifier.fillMaxSize()) {

        Button(onClick = { component.onLoadClicked() }) {
            Text(text = "Load")
        }

        Text(text = "CURRENT JOKE: ${model.value.stateDate.value.joke}")

        LazyColumn {
            items(model.value.allJokesState) { item ->
                Text(
                    text = item.joke,
                    modifier = Modifier.clickable {
                        component.onItemClicked(item = item.joke)
                    })
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun previewListContent() {
    ListContent(
        component = object : ListComponent {
            @SuppressLint("UnrememberedMutableState")
            override val model: Value<ListComponentModel> = MutableValue(
                ListComponentModel(
                    items = List(100) {
                        "Item $it"
                    },
                    stateDate = mutableStateOf<JokeModel>(JokeModel("")),
                    allJokesState = mutableStateListOf()
                )
            )

            override fun onItemClicked(item: String) { }

            override fun onLoadClicked() { }

        },
        modifier = Modifier
    )
}