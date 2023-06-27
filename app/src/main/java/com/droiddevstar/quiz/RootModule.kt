//package com.droiddevstar.quiz
//
//import android.content.Context
//import com.arkivanov.decompose.ComponentContext
//import com.droiddevstar.quiz.network.JokeApi
//import com.droiddevstar.quiz.root.DefaultRootComponent
//import com.droiddevstar.quiz.root.RootComponent
//import dagger.Module
//import dagger.Provides
//import dagger.hilt.InstallIn
//import dagger.hilt.android.components.ActivityComponent
//import dagger.hilt.android.qualifiers.ApplicationContext
//import dagger.hilt.components.SingletonComponent
//
//@InstallIn(SingletonComponent::class)
//@Module
//object RootModule {
//
//    @Provides
//    fun provideRootComponent(
//        componentContext: ComponentContext,
//        @ApplicationContext appContext: Context,
//        jokeApi: JokeApi
//    ): RootComponent {
//        return DefaultRootComponent(
//            componentContext = componentContext,
//            appContext = appContext,
//            jokeApi = jokeApi
//        )
//    }
//}