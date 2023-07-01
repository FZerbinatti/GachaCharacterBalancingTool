package com.dreamsphere.gachacharacterbalancingtool.ui.Screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController

@Composable
fun ScreenCharacterSpecs(character_name: String?, navController: NavController) {

    //lista con text + etextfield
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize()) {
        Column() {
            
            Text(text = "modifica personaggio: $character_name")
            Text(text = "Lista Specs Personaggio")
        }
        
        
    }
}