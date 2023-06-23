package com.droiddevstar.quiz

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Modifier
import com.arkivanov.decompose.extensions.compose.jetpack.subscribeAsState

@Composable
fun DetailsContent(
    component: DetailsComponent,
    modifier: Modifier
) {
    val model: State<DetailsComponent.Model> = component.model.subscribeAsState()

    Text(text = model.value.item)
}