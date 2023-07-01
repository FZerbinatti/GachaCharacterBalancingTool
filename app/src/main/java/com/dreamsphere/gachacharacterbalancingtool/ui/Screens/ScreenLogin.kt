package com.dreamsphere.gachacharacterbalancingtool.ui.Screens

import android.content.ContentValues.TAG
import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.dreamsphere.gachacharacterbalancingtool.ui.NavigationTools.Screen

@Composable
fun ScreenLogin(navController: NavController) {

    //3 pulsanti

    Column(
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
    ) {
        Button(onClick = {
                  navController.navigate(Screen.ScreenMainMenu.route)
            Log.d(TAG, "ScreenLogin: clicked")
        },
            modifier = Modifier.align(CenterHorizontally)) {
            Text(text = "Login")
        }
    }
}