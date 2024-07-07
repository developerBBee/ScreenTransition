package com.example.screentransition.myMode.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.screentransition.R
import com.example.screentransition.myMode.ui.util.getMultiString

@Composable
fun MyFirstScreen(
    onNextClick: () -> Unit = {},
    onBackClick: () -> Unit = {},
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = getMultiString(id = R.string.screen_1_headline),
            style = MaterialTheme.typography.headlineLarge
        )
        Button(onClick = onNextClick) {
            Text(text = getMultiString(id = R.string.next))
        }
        Button(onClick = onBackClick, enabled = false) {
            Text(text = getMultiString(id = R.string.back))
        }
    }
}
