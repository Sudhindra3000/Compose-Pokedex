package com.sudhindra.composepokedex.ui.utils

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import com.sudhindra.composepokedex.ui.theme.ComposePokedexTheme

@Composable
fun WithTheme(content: @Composable () -> Unit) {
    ComposePokedexTheme {
        Surface(color = MaterialTheme.colors.background) {
            content()
        }
    }
}
