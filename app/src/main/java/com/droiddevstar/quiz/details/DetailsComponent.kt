package com.droiddevstar.quiz.details

import com.arkivanov.decompose.value.Value

interface DetailsComponent {

    val model: Value<Model>



    data class Model(
        val item: String
    )
}