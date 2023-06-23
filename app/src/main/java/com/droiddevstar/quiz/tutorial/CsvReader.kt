package com.droiddevstar.quiz.tutorial

import android.content.Context
import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*

class CsvReader constructor(val applicationContext: Context) {

    fun readCsv(): ArrayList<CountryDbModel> {
        val nameId = 0
        val capitalId = 1
        val areaId = 2
        val populationId = 3
        val continentId = 4
        val flagFileName = 5


        val fileReader: BufferedReader?

        val countriesList = ArrayList<CountryDbModel>()
        var line: String?

        val locale: Locale = Locale.getDefault()

        val currentLanguage: String = when (locale.toString().substring(0, 2)) {
            LANGUAGE_RUSSIAN -> {
                LANGUAGE_RUSSIAN
            }
            LANGUAGE_UKRAINIAN -> {
                LANGUAGE_UKRAINIAN
            }
            LANGUAGE_GERMAN -> {
                LANGUAGE_GERMAN
            }
            else -> {
                LANGUAGE_ENGLISH
            }
        }

        val fileName = "countries_${currentLanguage}.csv"
        val inputStream = applicationContext.assets.open(fileName)

        fileReader = BufferedReader(InputStreamReader(inputStream, "UTF-8"))

        fileReader.use {
            line = it.readLine()

            while (line != null) {
                val tokens = line?.split(",")
                if (null != tokens
                    && tokens.isNotEmpty()) {
                    val countryModel =
                        CountryDbModel(
                            tokens[nameId],
                            tokens[capitalId],
                            tokens[continentId],
                            tokens[continentId],
                            Integer.parseInt(tokens[populationId].replace(" ", "")),
                            tokens[flagFileName],
                            false
                        )

                    countriesList.add(countryModel)
                }

                line = it.readLine()
            }

            return countriesList
        }
    }

}

const val LANGUAGE_RUSSIAN = "ru"
const val LANGUAGE_UKRAINIAN = "uk"
const val LANGUAGE_ENGLISH = "en"
const val LANGUAGE_GERMAN = "de"
