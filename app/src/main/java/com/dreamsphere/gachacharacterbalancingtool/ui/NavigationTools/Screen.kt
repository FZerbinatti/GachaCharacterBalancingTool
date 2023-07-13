package com.dreamsphere.gachacharacterbalancingtool.ui.NavigationTools

import com.dreamsphere.gachacharacterbalancingtool.Models.Objects.Character

sealed class Screen(val route: String){
    object ScreenLogin: Screen("screen_login")
    object ScreenMainMenu: Screen("screen_main_menu")
    object ScreenCharacters: Screen("screen_characters")
    object ScreenNewCharacher: Screen("screen_new_character")
    object ScreenCharacterSpecs: Screen("screen_character_specs")

    object ScreenNewClasss: Screen("screen_new_classs")



    fun withArgs(vararg args: String): String{
        return buildString {
            append(route)
            args.forEach{ arg ->
                append("/$arg")
            }
        }
    }

    fun withArgs(vararg args: Int): String{
        return buildString {
            append(route)
            args.forEach{ arg ->
                append("/$arg")
            }
        }
    }

    fun withArgs(vararg args: Character): String{
        return buildString {
            append(route)
            args.forEach{ arg ->
                append("/$arg")
            }
        }
    }
}
