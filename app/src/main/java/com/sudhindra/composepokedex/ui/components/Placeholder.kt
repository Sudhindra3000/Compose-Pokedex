package com.sudhindra.composepokedex.ui.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun Placeholder(modifier: Modifier) {
    Box(
        modifier,
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator()
    }
}

@Composable
fun RetryButton(
    modifier: Modifier,
    onClick: () -> Unit
) {
    Box(
        modifier,
        contentAlignment = Alignment.Center
    ) {
        IconButton(
            modifier = Modifier.border(1.dp, MaterialTheme.colors.onBackground, CircleShape),
            onClick = onClick
        ) {
            Icon(imageVector = Icons.Default.Refresh, contentDescription = "Retry Button")
        }
    }
}
