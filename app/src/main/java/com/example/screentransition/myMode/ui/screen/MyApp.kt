package com.example.screentransition.myMode.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.window.Dialog
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.screentransition.myMode.ui.MyState
import com.example.screentransition.myMode.ui.component.MyScaffold
import com.example.screentransition.myMode.ui.navigation.MyNavHost
import com.example.screentransition.myMode.viewmodel.MyViewModel
import java.util.Locale

@Composable
fun MyAppScreen(
    myState: MyState,
    modifier: Modifier = Modifier,
    viewModel: MyViewModel = hiltViewModel(),
    onChangeLocale: (Locale) -> Unit,
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    MyScaffold(
        modifier = modifier,
        currentRoute = myState.currentRoute,
        onTopBarActionButtonClick = { viewModel.switchLocaleDialog(show = true) }
    ) { paddingValues ->
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues = paddingValues)
        ) {
            MyNavHost(myState)
        }
    }

    if (uiState.showLocaleDialog) {
        Dialog(onDismissRequest = { viewModel.switchLocaleDialog(show = false) }) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Button(onClick = { onChangeLocale(Locale.JAPANESE) }) {
                    Text(text = "Japanese")
                }
                Button(onClick = { onChangeLocale(Locale.ENGLISH) }) {
                    Text(text = "English")
                }
            }
        }
    }
}
