package com.droiddevstar.quiz

import android.content.Context
import androidx.activity.ComponentActivity
import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.defaultComponentContext
import com.droiddevstar.quiz.repository.JokesRepository
import com.droiddevstar.quiz.root.DefaultRootComponent
import com.droiddevstar.quiz.root.RootComponent
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.qualifiers.ActivityContext
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.android.scopes.ActivityScoped

@InstallIn(ActivityComponent::class)
@Module
object AppModule {

    @Provides
    @ActivityScoped
    fun provideDefaultComponentContext(
        @ActivityContext context: Context
    ): ComponentContext {
        return (context as ComponentActivity).defaultComponentContext()
    }

    @Provides
    @ActivityScoped
    fun provideRootComponent(
        componentContext: ComponentContext,
        @ApplicationContext appContext: Context,
        jokesRepository: JokesRepository
    ): RootComponent {
        return DefaultRootComponent(
            componentContext = componentContext,
            appContext = appContext,
            jokesRepository = jokesRepository
        )
    }
}