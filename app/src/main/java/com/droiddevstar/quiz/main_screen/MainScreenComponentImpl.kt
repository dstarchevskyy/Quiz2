package com.droiddevstar.quiz.main_screen

class MainScreenComponentImpl(
    private val onButtonClicked: (navItem: NavItem) -> Unit
) : MainScreenComponent {

    override fun onItemClicked(item: NavItem) {
        println("@@@MainScreenComponentImpl: $item")
        when(item) {
            NavItem.TUTORIAL -> {
                onButtonClicked(item)
            }
            else -> println("@@@not implemented")
        }
    }
}