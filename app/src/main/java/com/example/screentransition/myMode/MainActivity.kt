package com.example.screentransition.myMode

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.rememberNavController
import com.example.screentransition.myMode.ui.MyState
import com.example.screentransition.myMode.ui.rememberMyState
import com.example.screentransition.myMode.ui.screen.MyAppScreen
import com.example.screentransition.myMode.ui.theme.ScreenTransitionTheme
import com.example.screentransition.myMode.ui.util.LocalContextDynamicLocale
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val myState = rememberMyState(navController = rememberNavController())
            Log.d("MainActivity", "myState: $myState")

            MyApp(myState = myState)
        }
    }
}

@Composable
fun MyApp(myState: MyState) {
    val locale by myState.currentLocale.collectAsStateWithLifecycle()
    Log.d("MainActivity", "updateLocale: $locale")

    val context = LocalContext.current
    val configuration = context.resources.configuration
    configuration.setLocale(locale)
    val localizedContext = context.createConfigurationContext(configuration)

    CompositionLocalProvider(value = LocalContextDynamicLocale provides localizedContext) {
        ScreenTransitionTheme {
            MyAppScreen(
                myState = myState,
                onChangeLocale = { newLocale -> myState.updateLocale(newLocale) }
            )
        }
    }
}