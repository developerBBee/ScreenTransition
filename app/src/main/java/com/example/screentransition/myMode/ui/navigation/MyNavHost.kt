package com.example.screentransition.myMode.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.screentransition.myMode.ui.MyState
import com.example.screentransition.myMode.ui.screen.MyFifthScreen
import com.example.screentransition.myMode.ui.screen.MyFirstScreen
import com.example.screentransition.myMode.ui.screen.MyFourthScreen
import com.example.screentransition.myMode.ui.screen.MySecondScreen
import com.example.screentransition.myMode.ui.screen.MyThirdScreen

@Composable
fun MyNavHost(
    myState: MyState,
    modifier: Modifier = Modifier,
) {
    val navController = myState.navController
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = MyRoute.Screen1.route
    ) {
        composable(MyRoute.Screen1.route) {
            MyFirstScreen(
                onNextClick = { navController.navigate(MyRoute.Screen2.route) },
            )
        }
        composable(MyRoute.Screen2.route) {
            MySecondScreen(
                onNextClick = { navController.navigate(MyRoute.Screen3.route) },
                onBackClick = { navController.popBackStack() },
            )
        }
        composable(MyRoute.Screen3.route) {
            MyThirdScreen(
                onNextClick = { navController.navigate(MyRoute.Screen4.route) },
                onBackClick = { navController.popBackStack() },
            )
        }
        composable(MyRoute.Screen4.route) {
            MyFourthScreen(
                onNextClick = { navController.navigate(MyRoute.Screen5.route) },
                onBackClick = { navController.popBackStack() },
            )
        }
        composable(MyRoute.Screen5.route) {
            MyFifthScreen(
                onNextClick = { navController.navigatePopToFirst() },
                onBackClick = { navController.popBackStack() },
            )
        }
    }
}

fun NavController.navigatePopToFirst() {
    popBackStack(route = MyRoute.Screen1.route, inclusive = false)
}