package com.example.screentransition.myMode.ui.util

import android.content.Context
import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import androidx.compose.runtime.staticCompositionLocalOf

val LocalContextDynamicLocale = staticCompositionLocalOf<Context> {
    error("No context provided")
}

@Composable
fun getMultiString(@StringRes id: Int): String {
    return LocalContextDynamicLocale.current.getString(id)
}
