package com.droiddevstar.quiz

import androidx.compose.foundation.clickable
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Modifier
import com.arkivanov.decompose.extensions.compose.jetpack.subscribeAsState


@Composable
fun ListContent(
    component: ListComponent,
    modifier: Modifier = Modifier
) {
    val model: State<ListComponent.Model> = component.model.subscribeAsState()

    LazyColumn {
        items(model.value.items) { item ->
            Text(text = item.toString())
        }
    }
}