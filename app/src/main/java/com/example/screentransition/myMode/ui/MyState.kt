package com.example.screentransition.myMode.ui

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.navigation.NavDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.screentransition.myMode.ui.navigation.MyRoute
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import java.util.Locale

@Composable
fun rememberMyState(navController: NavHostController) = MyState(navController)

@Stable
class MyState(
    val navController: NavHostController,
) {
    val currentDestination: NavDestination?
    @Composable get() = navController
        .currentBackStackEntryAsState().value?.destination

    val currentRoute: MyRoute?
    @Composable get() = when (currentDestination?.route) {
        MyRoute.Screen1.route -> MyRoute.Screen1
        MyRoute.Screen2.route -> MyRoute.Screen2
        MyRoute.Screen3.route -> MyRoute.Screen3
        MyRoute.Screen4.route -> MyRoute.Screen4
        MyRoute.Screen5.route -> MyRoute.Screen5
        else -> null
    }

    private val _currentLocale: MutableStateFlow<Locale> = MutableStateFlow(Locale.getDefault())
    val currentLocale: StateFlow<Locale> = _currentLocale.asStateFlow()

    fun updateLocale(locale: Locale) {
        Log.d("MyState", "updateLocale: $locale")
        _currentLocale.value = locale
    }
}
