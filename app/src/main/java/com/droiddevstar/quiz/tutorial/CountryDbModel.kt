package com.droiddevstar.quiz.tutorial

data class CountryDbModel(

    val name: String,

    val capital: String?,

    val region: String?,

    val subregion: String?,

    val population: Int,

    val flagFileName: String,

    val guessed: Boolean = false,

    )

const val COUNTRIES_TABLE = "Countries"