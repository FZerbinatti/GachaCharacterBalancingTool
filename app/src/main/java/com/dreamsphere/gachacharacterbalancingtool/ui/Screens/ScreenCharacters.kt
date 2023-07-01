package com.dreamsphere.gachacharacterbalancingtool.ui.Screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.rounded.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Popup
import androidx.compose.ui.window.PopupProperties
import androidx.navigation.NavController
import com.dreamsphere.gachacharacterbalancingtool.R
import com.dreamsphere.gachacharacterbalancingtool.ui.NavigationTools.Screen
import com.dreamsphere.gachacharacterbalancingtool.ui.theme.GachaCharacterBalancingToolTheme
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun ScreenCharacters(navController: NavController) {
    //lista di tutti i characters
    var characther_name by remember { mutableStateOf("characther_name") }
    characther_name = "characther_name"
    GachaCharacterBalancingToolTheme() {
        Scaffold(
            floatingActionButton = {
                val context = LocalContext.current
                FloatingActionButton(
                    backgroundColor = colorResource(id = R.color.white),
                    onClick = {
                        //vai alla pagina di creazione pg
                        navController.navigate(Screen.ScreenNewCharacher.route)

                    }) {
                    Icon(Icons.Default.Add, contentDescription = null)
                }
            },
            content = {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    //MainView(viewModel, context)
                    Column() {


                        Text(text = "Lista di personaggi")

                    }
                }
            }
        )
    }


















    ///////////////////////////////////////////////////////////////////////
/*    Column(
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
    ) {

        Button(onClick = {
            navController.navigate(Screen.ScreenCharacterSpecs.withArgs(characther_name))

        },
            modifier = Modifier.align(Alignment.CenterHorizontally)) {
            Text(text = "go to character")

        }
        Scaffold(
            FloatingActionButton(
                onClick = {
                    //OnClick Method
                },
                backgroundColor = MaterialTheme.colors.secondary,
                shape = RoundedCornerShape(16.dp),
            ) {
                Icon(
                    imageVector = Icons.Rounded.Add,
                    contentDescription = "Add FAB",
                    tint = Color.White,
                )
            }
        ) {
            
        }
    }*/
}