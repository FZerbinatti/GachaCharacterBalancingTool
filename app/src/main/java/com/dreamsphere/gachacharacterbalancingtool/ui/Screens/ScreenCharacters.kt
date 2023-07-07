package com.dreamsphere.gachacharacterbalancingtool.ui.Screens

import android.annotation.SuppressLint
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.dreamsphere.gachacharacterbalancingtool.R
import com.dreamsphere.gachacharacterbalancingtool.ViewModels.ViewModel
import com.dreamsphere.gachacharacterbalancingtool.ui.NavigationTools.Screen
import com.dreamsphere.gachacharacterbalancingtool.ui.Views.CardCharacter
import com.dreamsphere.gachacharacterbalancingtool.ui.theme.GachaCharacterBalancingToolTheme

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@OptIn(ExperimentalMaterialApi::class, ExperimentalFoundationApi::class)
@Composable
fun ScreenCharacters(navController: NavController, viewModel: ViewModel) {
    //lista di tutti i characters
    /*var characther_name by remember { mutableStateOf("characther_name") }
    characther_name = "characther_name"*/
    val characterListState = viewModel.charactersListFlow.collectAsState()

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


                        LazyColumn(
                            horizontalAlignment = Alignment.Start,
                            modifier = Modifier.padding(10.dp),
                            state = LazyListState()
                        ) {

                            itemsIndexed(viewModel.charactersList) { index, character ->
                                /*val moshi = Moshi.Builder().build()
                                val jsonAdapter = moshi.adapter(Character::class.java).lenient()
                                val charJson = jsonAdapter.toJson(character)*/

                                CardCharacter(navController, viewModel, character, index)
                            }

                        }
                    }
                }


            })
    }
}

