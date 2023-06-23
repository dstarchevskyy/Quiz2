package com.droiddevstar.quiz.main_screen

class MainScreenComponentImpl : MainScreenComponent {

    override fun onItemClicked(item: String) {
        println("@@@MainScreenComponentImpl: $item")
    }
}