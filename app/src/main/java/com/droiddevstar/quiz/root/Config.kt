package com.droiddevstar.quiz.root

import android.annotation.SuppressLint
import com.arkivanov.essenty.parcelable.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize // The `kotlin-parcelize` plugin must be applied if you are targeting Android
sealed interface Config : Parcelable {

    @SuppressLint("ParcelCreator")
    object Tutorial : Config
    @SuppressLint("ParcelCreator")
    object MainScreen : Config

    @SuppressLint("ParcelCreator")
    object List : Config

    @SuppressLint("ParcelCreator")
    data class Details(val item: String) : Config
}