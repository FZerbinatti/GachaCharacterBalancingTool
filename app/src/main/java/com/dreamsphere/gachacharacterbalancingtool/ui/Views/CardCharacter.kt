package com.dreamsphere.gachacharacterbalancingtool.ui.Views

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.dreamsphere.gachacharacterbalancingtool.Models.Objects.Character
import com.dreamsphere.gachacharacterbalancingtool.ViewModels.ViewModel
import com.dreamsphere.gachacharacterbalancingtool.ui.NavigationTools.Screen

@Composable
fun CardCharacter(
    navController: NavController,
    viewModel: ViewModel,
    character: Character,
    index: Int
) {
    Card(
        modifier = Modifier
            .padding(10.dp)
            .fillMaxWidth()
            .wrapContentHeight()
            .clickable {
                //viewModel.selectCharacter(index)
                //viewModel.character = character
                //Log.d("Main Card", "CardCharacter: recieved char: "+character)
                //Log.d("Main Card", "CardCharacter: viewmodel char before: "+viewModel._characterViewState.value)
                //viewModel._characterViewState.value = character
                // Log.d("Main Card", "CardCharacter: viewmodel char after: "+viewModel._characterViewState.value)


                //navController.navigate(Screen.ScreenNewCharacher.route)
                //navController.navigate(Screen.ScreenNewCharacher.withArgs(index.toString()))
            },

        shape = MaterialTheme.shapes.medium,
        elevation = 5.dp,
        backgroundColor = MaterialTheme.colors.surface
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
        ) {
            /*Image(
                painter = painterResource(id = image),
                contentDescription = null,
                modifier = Modifier.size(130.dp)
                    .padding(8.dp),
                contentScale = ContentScale.Fit,
            )*/
            Column(
                Modifier
                    .padding(8.dp)
                    .height(50.dp), verticalArrangement = Arrangement.Center) {
                Text(
                    text = character.character_name.toString(),
                    style = MaterialTheme.typography.h6,

                    color = MaterialTheme.colors.onSurface,
                    fontSize = 16.sp
                )

            }
        }
    }
}