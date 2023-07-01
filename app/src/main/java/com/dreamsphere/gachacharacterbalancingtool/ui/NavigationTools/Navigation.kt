package com.dreamsphere.gachacharacterbalancingtool.ui.NavigationTools

import android.content.ContentValues.TAG
import android.util.Log
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.dreamsphere.gachacharacterbalancingtool.ui.Screens.ScreenCharacterSpecs
import com.dreamsphere.gachacharacterbalancingtool.ui.Screens.ScreenCharacters
import com.dreamsphere.gachacharacterbalancingtool.ui.Screens.ScreenLogin
import com.dreamsphere.gachacharacterbalancingtool.ui.Screens.ScreenMainMenu
import com.dreamsphere.gachacharacterbalancingtool.ui.Screens.ScreenNewCharacher

@Composable
fun Navigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screen.ScreenLogin.route) {

        composable(route = Screen.ScreenLogin.route) {
            ScreenLogin(navController = navController)
        }

        composable(route = Screen.ScreenMainMenu.route) {
            ScreenMainMenu(navController = navController)
        }

        composable(route = Screen.ScreenCharacters.route) {
            ScreenCharacters(navController = navController)
        }

        composable(route = Screen.ScreenNewCharacher.route) {
            ScreenNewCharacher(navController = navController)
        }

        composable(
            route = Screen.ScreenCharacterSpecs.route +"/{characther_name}",
            arguments = listOf(
                navArgument("characther_name") {
                    type = NavType.StringType
                    defaultValue = "NoCharacter"
                    nullable = true
                }
            )
        ){entry->
            Log.d(TAG, "Navigation: "+entry)
            ScreenCharacterSpecs(character_name = entry.arguments?.getString("characther_name"), navController)
        }
    }
}