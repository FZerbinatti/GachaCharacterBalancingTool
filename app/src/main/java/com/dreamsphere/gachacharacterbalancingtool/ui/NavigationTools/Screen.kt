package com.dreamsphere.gachacharacterbalancingtool.ui.NavigationTools

sealed class Screen(val route: String){
    object ScreenLogin: Screen("screen_login")
    object ScreenMainMenu: Screen("screen_main_menu")
    object ScreenCharacters: Screen("screen_characters")
    object ScreenNewCharacher: Screen("screen_new_character")
    object ScreenCharacterSpecs: Screen("screen_character_specs")
    object ScreenCharacterAbility: Screen("screen_character_ability")

    fun withArgs(vararg args: String): String{
        return buildString {
            append(route)
            args.forEach{ arg ->
                append("/$arg")
            }
        }
    }
}
