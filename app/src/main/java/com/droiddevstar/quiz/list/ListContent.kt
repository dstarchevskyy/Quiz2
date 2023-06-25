package com.droiddevstar.quiz.list

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.arkivanov.decompose.extensions.compose.jetpack.subscribeAsState
import com.arkivanov.decompose.value.MutableValue
import com.arkivanov.decompose.value.Value

@Composable
fun ListContent(
    component: ListComponent,
    modifier: Modifier = Modifier
) {
    val model: State<ListComponent.Model> = component.model.subscribeAsState()

    Column(Modifier.fillMaxSize()) {

        Button(onClick = { component.onLoadClicked() }) {
            Text(text = "Load")
        }

        LazyColumn {
            items(model.value.items) { item ->
                Text(
                    text = item,
                    modifier = Modifier.clickable {
                        component.onItemClicked(item = item)
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
            override val model: Value<ListComponent.Model> = MutableValue(
                ListComponent.Model(
                    items = List(100) {
                        "Item $it"
                    }
                )
            )

            override fun onItemClicked(item: String) { }

            override fun onLoadClicked() { }

        },
        modifier = Modifier
    )
}