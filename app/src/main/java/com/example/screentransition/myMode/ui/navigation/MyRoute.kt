package com.example.screentransition.myMode.ui.navigation

import com.example.screentransition.R

sealed class MyRoute(val route: String, val titleId: Int) {
    data object Screen1 : MyRoute(route = "screen1", titleId = R.string.screen_1_title)
    data object Screen2 : MyRoute(route = "screen2", titleId = R.string.screen_2_title)
    data object Screen3 : MyRoute(route = "screen3", titleId = R.string.screen_3_title)
    data object Screen4 : MyRoute(route = "screen4", titleId = R.string.screen_4_title)
    data object Screen5 : MyRoute(route = "screen5", titleId = R.string.screen_5_title)
}