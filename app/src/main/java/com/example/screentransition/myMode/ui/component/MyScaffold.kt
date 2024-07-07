package com.example.screentransition.myMode.ui.component

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.screentransition.myMode.ui.navigation.MyRoute
import com.example.screentransition.myMode.ui.util.getMultiString

@Composable
fun MyScaffold(
    modifier: Modifier = Modifier,
    currentRoute: MyRoute? = null,
    onTopBarActionButtonClick: () -> Unit = {},
    content: @Composable (PaddingValues) -> Unit
) {
    Scaffold(
        modifier = modifier,
        topBar = { MyTopBar(
            title = currentRoute.getTitle(),
            onActionButtonClick = onTopBarActionButtonClick
        ) },
        bottomBar = { MyBottomBar() },
        snackbarHost = { Text(text = "SnackBarHost") },
        floatingActionButton = { Icon(imageVector = Icons.Filled.Add, contentDescription = "Add") },
        containerColor = MaterialTheme.colorScheme.scrim,
        contentColor = MaterialTheme.colorScheme.onBackground,
        contentWindowInsets = WindowInsets(0, 0, 0, 0)
    ) { padding ->
        content(padding)
    }
}

@Composable
fun MyRoute?.getTitle(): String = this?.titleId?.let { getMultiString(id = it) } ?: ""