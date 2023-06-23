package com.droiddevstar.quiz.tutorial

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.droiddevstar.quiz.R
import com.droiddevstar.quiz.ui.theme.QuizTheme

@Composable
fun TutorialScreen() {
    Text(text = stringResource(id = R.string.tutorial))
}


@Preview(showBackground = true)
@Composable
fun TutorialScreenPreview() {
    QuizTheme {
        TutorialScreen()
    }
}