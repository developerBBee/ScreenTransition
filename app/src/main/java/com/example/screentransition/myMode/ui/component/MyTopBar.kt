package com.example.screentransition.myMode.ui.component

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LargeTopAppBar
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.screentransition.myMode.ui.theme.ScreenTransitionTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyTopBar(
    title: String,
    modifier: Modifier = Modifier,
    onActionButtonClick: () -> Unit = {}
) {
    LargeTopAppBar(
        modifier = modifier,
        colors = TopAppBarDefaults.largeTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer,
            actionIconContentColor = MaterialTheme.colorScheme.onPrimaryContainer,
        ),
        scrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior(),
        title = { Text(text = title) },
        actions = {
            Text(text = "Action")
            IconButton(onClick = onActionButtonClick) {
                Icon(imageVector = Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
            }
        }
    )
}

@Preview
@Composable
fun MyTopBarLightPreview() {
    ScreenTransitionTheme(darkTheme = false) {
        MyTopBar(title = "Hello Light Top Bar")
    }
}

@Preview
@Composable
fun MyTopBarDarkPreview() {
    ScreenTransitionTheme(darkTheme = true) {
        MyTopBar(title = "Hello Dark Top Bar")
    }
}