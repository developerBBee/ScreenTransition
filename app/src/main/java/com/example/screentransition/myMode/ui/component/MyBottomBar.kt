package com.example.screentransition.myMode.ui.component

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Send
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.BottomAppBarDefaults.exitAlwaysScrollBehavior
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.screentransition.myMode.ui.theme.ScreenTransitionTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyBottomBar(
    modifier: Modifier = Modifier,
    onActionButtonClick: () -> Unit = {}
) {
    BottomAppBar(
        modifier = modifier,
        actions = {
            Text(text = "Action")
            IconButton(onClick = onActionButtonClick) {
                Icon(imageVector = Icons.AutoMirrored.Filled.Send, contentDescription = "Back")
            }
        },
        scrollBehavior = exitAlwaysScrollBehavior(),
    )
}

@Preview
@Composable
private fun MyBottomBarLightPreview() {
    ScreenTransitionTheme(darkTheme = false) {
        MyBottomBar()
    }
}

@Preview
@Composable
private fun MyBottomDarkPreview() {
    ScreenTransitionTheme(darkTheme = true) {
        MyBottomBar()
    }
}