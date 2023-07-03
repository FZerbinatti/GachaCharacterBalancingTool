package com.dreamsphere.gachacharacterbalancingtool.ui.Screens

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.Popup
import androidx.compose.ui.window.PopupProperties
import androidx.navigation.NavController
import com.dreamsphere.gachacharacterbalancingtool.ViewModels.ViewModel
import com.dreamsphere.gachacharacterbalancingtool.ui.Views.PersonalizedAlertDialog
import com.dreamsphere.gachacharacterbalancingtool.ui.Views.WindowCenterOffsetPositionProvider
import kotlinx.coroutines.launch

@Composable
fun ScreenNewCharacher(navController: NavController, viewModel: ViewModel) {


    val int_spacer = 10.dp
    val scrollState = rememberScrollState()
    var showDialog = remember { mutableStateOf(false) }
    var type: String = ""
    var faction = remember { mutableStateOf("") }
    val context = LocalContext.current
    val generalsListState = viewModel.generalListFlow.collectAsState()
    val lazyListState = rememberLazyListState()
    val scope = rememberCoroutineScope()



    //firebase
    val FACTIONS = "factions"
    val CLASSES = "classes"
    val TIERS = "tiers"


    var character_name by remember { mutableStateOf(TextFieldValue("")) }
    var character_faction by remember { mutableStateOf(TextFieldValue("")) }
    var character_description by remember { mutableStateOf(TextFieldValue("")) }
    var character_class by remember { mutableStateOf(TextFieldValue("")) }
    var character_tier by remember { mutableStateOf(TextFieldValue("")) }
    var character_hp by remember { mutableStateOf(TextFieldValue("")) }
    var character_atk by remember { mutableStateOf(TextFieldValue("")) }
    var character_def by remember { mutableStateOf(TextFieldValue("")) }
    var character_avatar by remember { mutableStateOf(TextFieldValue("")) }

    Column(

        verticalArrangement = Arrangement.Center,

        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
            .verticalScroll(
                state = scrollState,

                )
    ) {
        Text(text = "Creazione nuovo personaggio")

        //ScrollView
        if (showDialog.value){


            Popup(
                popupPositionProvider = WindowCenterOffsetPositionProvider(),
                onDismissRequest = { showDialog.value = false },
                properties = PopupProperties(focusable = true)
            ) {
                PersonalizedAlertDialog({
                    showDialog.value = false
                    scope.launch {
                        lazyListState.scrollToItem(generalsListState.value.size)
                    }
                }, viewModel)
            }
        }




        // ------------------------------------- boxes --------------------------------------------------
        OutlinedTextField(
            value = character_name,
            label = { Text(text = "character_name") },
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White),
            onValueChange = { character_name = it })

        Spacer(modifier = Modifier.padding(int_spacer))
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier
                .height(60.dp)
                .fillMaxWidth()
                .border(width = 1.dp, color = Color.Gray, shape = RoundedCornerShape(5.dp))
                .clip(RoundedCornerShape(5.dp))
                .background(Color.White),
            ){
            ClickableText(
                AnnotatedString(viewModel.faction.value),
                onClick = {
                    type = FACTIONS
                    Log.d("Main", "ScreenNewCharacher 1: " + type)
                    viewModel.getHardCodedDataFromFirebase(type)
                    showDialog.value = true },
                modifier = Modifier.fillMaxWidth().padding(10.dp))
        }
        Spacer(modifier = Modifier.padding(int_spacer))

        OutlinedTextField(
            value = character_description,
            label = { Text(text = "character_description") },
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White),
            onValueChange = { character_description = it })
        Spacer(modifier = Modifier.padding(int_spacer))

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier
                .height(60.dp)
                .fillMaxWidth()
                .border(width = 1.dp, color = Color.Gray, shape = RoundedCornerShape(5.dp))
                .clip(RoundedCornerShape(5.dp))
                .background(Color.White),
        ){
            ClickableText(
                AnnotatedString(viewModel.classs.value),
                onClick = {
                    type = CLASSES
                    viewModel.getHardCodedDataFromFirebase(type)
                    showDialog.value = true },
                modifier = Modifier.fillMaxWidth().padding(10.dp))
        }
        Spacer(modifier = Modifier.padding(int_spacer))

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier
                .height(60.dp)
                .fillMaxWidth()
                .border(width = 1.dp, color = Color.Gray, shape = RoundedCornerShape(5.dp))
                .clip(RoundedCornerShape(5.dp))
                .background(Color.White),
        ){
            ClickableText(
                AnnotatedString(viewModel.tier.value),
                onClick = {
                    type = TIERS
                    Log.d("Main", "ScreenNewCharacher 1: " + type)
                    viewModel.getHardCodedDataFromFirebase(type)
                    showDialog.value = true },
                modifier = Modifier.fillMaxWidth().padding(10.dp))
        }
        Spacer(modifier = Modifier.padding(int_spacer))

        OutlinedTextField(
            value = character_hp,
            label = { Text(text = "character_hp") },
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White),
            onValueChange = { character_hp = it })

        Spacer(modifier = Modifier.padding(int_spacer))

        OutlinedTextField(
            value = character_atk,
            label = { Text(text = "character_atk") },
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White),
            onValueChange = { character_atk = it })
        Spacer(modifier = Modifier.padding(int_spacer))

        OutlinedTextField(
            value = character_def,
            label = { Text(text = "character_def") },
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White),
            onValueChange = { character_def = it })
        Spacer(modifier = Modifier.padding(int_spacer))

        OutlinedTextField(
            value = character_avatar,
            label = { Text(text = "character_avatar") },
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White),
            onValueChange = { character_avatar = it })
        Spacer(modifier = Modifier.padding(int_spacer))


    }
}




