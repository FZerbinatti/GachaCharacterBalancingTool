package com.dreamsphere.gachacharacterbalancingtool.ui.NavigationTools

import android.content.ContentValues.TAG
import android.util.Log
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.dreamsphere.gachacharacterbalancingtool.ViewModels.ViewModel
import com.dreamsphere.gachacharacterbalancingtool.ui.Screens.ScreenCharacterSpecs
import com.dreamsphere.gachacharacterbalancingtool.ui.Screens.ScreenCharacters
import com.dreamsphere.gachacharacterbalancingtool.ui.Screens.ScreenLogin
import com.dreamsphere.gachacharacterbalancingtool.ui.Screens.ScreenMainMenu
import com.dreamsphere.gachacharacterbalancingtool.ui.Screens.ScreenNewCharacher
import com.dreamsphere.gachacharacterbalancingtool.ui.Screens.ScreenNewClass

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
            ScreenCharacters(navController = navController,viewModel = ViewModel())
        }

        composable(route = Screen.ScreenNewClasss.route) {
            ScreenNewClass(navController = navController)
        }

        /*composable(route = Screen.ScreenNewCharacher.route) {
            ScreenNewCharacher(navController = navController,viewModel = ViewModel())
        }*/

        composable(
            route = Screen.ScreenNewCharacher.route +"/{index}",
            arguments = listOf(
                navArgument("characther_name") {
                    type = NavType.StringType
                    defaultValue = "-1"
                    nullable = true
                }
            )
        ){entry->
            Log.d(TAG, "Navigation: "+entry)
            ScreenNewCharacher(navController, viewModel = ViewModel(), index = entry.arguments?.getString("index"))
        }


/*        composable(
            route = Screen.ScreenCharacterAbility.route +"/{ability_name}",
            arguments = listOf(
                navArgument("ability_name") {
                    type = NavType.StringType
                    defaultValue = "New Ability"
                    nullable = true
                }
            )
        ){entry->
            Log.d(TAG, "Navigation: "+entry)
            ScreenCharacterAbility(ability_name = entry.arguments?.getString("ability_name"), navController, viewModel = ViewModel())
        }*/

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