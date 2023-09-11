package com.rafarocket.fce.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import com.rafarocket.fce.ui.theme.FetchCodingExerciseByRafaTheme
import dagger.hilt.android.AndroidEntryPoint

/**
 * Main Activity of the app.
 */
@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: HiringViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FetchCodingExerciseByRafaTheme {
                HiringListScreen(hiringViewModel = viewModel)
            }
        }
    }
}