package com.dreamsphere.gachacharacterbalancingtool.ui.Screens

import android.annotation.SuppressLint
import android.content.ContentValues.TAG
import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.input.key.KeyEvent
import androidx.compose.ui.input.key.key
import androidx.compose.ui.input.key.onKeyEvent
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Popup
import androidx.compose.ui.window.PopupProperties
import androidx.navigation.NavController
import com.dreamsphere.gachacharacterbalancingtool.Models.Objects.Ability
import com.dreamsphere.gachacharacterbalancingtool.Models.Objects.AbilityEffect
import com.dreamsphere.gachacharacterbalancingtool.Models.Objects.Character
import com.dreamsphere.gachacharacterbalancingtool.R
import com.dreamsphere.gachacharacterbalancingtool.ViewModels.ViewModel
import com.dreamsphere.gachacharacterbalancingtool.ui.NavigationTools.Screen
import com.dreamsphere.gachacharacterbalancingtool.ui.Views.PersonalizedAlertDialogGenerals
import com.dreamsphere.gachacharacterbalancingtool.ui.Views.PersonalizedAlertDialogNewAbility
import com.dreamsphere.gachacharacterbalancingtool.ui.Views.WindowCenterOffsetPositionProvider
import com.dreamsphere.gachacharacterbalancingtool.ui.theme.GachaCharacterBalancingToolTheme
import com.dreamsphere.sharedshoplistk.repository.Firebase.Firebase
import kotlinx.coroutines.launch

@OptIn(ExperimentalComposeUiApi::class)
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun ScreenNewCharacher(navController: NavController, viewModel: ViewModel) {


    val int_spacer = 10.dp
    val scrollState = rememberScrollState()
    var showDialogGenerals = remember { mutableStateOf(false) }
    var showDialogNewAbility = remember { mutableStateOf(false) }
    var type: String = ""
    var faction = remember { mutableStateOf("") }
    val context = LocalContext.current
    val generalsListState = viewModel.generalListFlow.collectAsState()
    val lazyListState = rememberLazyListState()
    val scope = rememberCoroutineScope()

    val abilityListState = viewModel.abilityListFlow.collectAsState()
    val focusManager = LocalFocusManager.current

    //firebase
    val FACTIONS = "factions"
    val CLASSES = "classes"
    val TIERS = "tiers"

    //var current_character = viewModel.characterViewState.value


    var ability_name = "New Ability"
    var character_name by remember { mutableStateOf(TextFieldValue("")) }
    var character_faction by remember { mutableStateOf(TextFieldValue("")) }
    var character_description by remember { mutableStateOf(TextFieldValue("")) }
    var character_class by remember { mutableStateOf(TextFieldValue("")) }
    var character_tier by remember { mutableStateOf(TextFieldValue("")) }
    var character_hp by remember { mutableStateOf(TextFieldValue("")) }
    var character_atk by remember { mutableStateOf(TextFieldValue("")) }
    var character_def by remember { mutableStateOf(TextFieldValue("")) }
    var character_avatar by remember { mutableStateOf(TextFieldValue("")) }
    /*Log.d(TAG, "ScreenNewCharacher !!!!!!!!!!!!!!!!!!!!!!!: "+current_character.character_name)
    if (!current_character.character_name?.isEmpty()!!){
        Log.d(TAG, "ScreenNewCharacher: ????????????????????????????????????????????")
        character_name= TextFieldValue(current_character.character_name.toString())
    }*/



    GachaCharacterBalancingToolTheme() {
        Scaffold(
            floatingActionButton = {
                val context = LocalContext.current
                FloatingActionButton(

                    backgroundColor = colorResource(id = R.color.white),
                    onClick = {
                        //open alert dialog ability
                        //navController.navigate(Screen.ScreenCharacterAbility.withArgs(ability_name))
                        showDialogNewAbility.value = true

                    }) {
                    //Icon(Icons.Default.Add, contentDescription = null)
                    Text(text = "Add Ability", modifier = Modifier.padding(10.dp))
                }
            },
            content = {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    //MainView(viewModel, context)
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
                        if (showDialogGenerals.value) {


                            Popup(
                                popupPositionProvider = WindowCenterOffsetPositionProvider(),
                                onDismissRequest = { showDialogGenerals.value = false },
                                properties = PopupProperties(focusable = true)
                            ) {
                                PersonalizedAlertDialogGenerals({
                                    showDialogGenerals.value = false
                                    scope.launch {
                                        lazyListState.scrollToItem(generalsListState.value.size)
                                    }
                                }, viewModel, type)
                            }
                        }
                        if (showDialogNewAbility.value) {
                            Popup(
                                popupPositionProvider = WindowCenterOffsetPositionProvider(),
                                onDismissRequest = { showDialogNewAbility.value = false },
                                properties = PopupProperties(focusable = true)
                            ) {
                                PersonalizedAlertDialogNewAbility({
                                    showDialogNewAbility.value = false
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
                            onValueChange = { character_name = it },

                            modifier = Modifier
                                .fillMaxWidth()
                                .background(Color.White),
                                /*.onKeyEvent {
                                    if (it.key == Key.Enter) {
                                        focusManager.moveFocus(FocusDirection.Next)
                                        true
                                    }else{false}
                                },*/
                            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
                            maxLines = 1


                        )

                        Spacer(modifier = Modifier.padding(int_spacer))
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Center,
                            modifier = Modifier
                                .height(60.dp)
                                .fillMaxWidth()
                                .border(
                                    width = 1.dp,
                                    color = Color.Gray,
                                    shape = RoundedCornerShape(5.dp)
                                )
                                .clip(RoundedCornerShape(5.dp))
                                .background(Color.White),
                        ) {
                            ClickableText(
                                AnnotatedString(viewModel.faction.value),
                                onClick = {
                                    type = FACTIONS
                                    Log.d("Main", "ScreenNewCharacher 1: " + type)
                                    viewModel.getHardCodedDataFromFirebase(type)
                                    showDialogGenerals.value = true
                                },
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(10.dp)
                            )
                        }
                        Spacer(modifier = Modifier.padding(int_spacer))

                        OutlinedTextField(
                            value = character_description,
                            label = { Text(text = "character_description") },
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(Color.White),
                            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),

                            onValueChange = { character_description = it })
                        Spacer(modifier = Modifier.padding(int_spacer))

                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Center,
                            modifier = Modifier
                                .height(60.dp)
                                .fillMaxWidth()
                                .border(
                                    width = 1.dp,
                                    color = Color.Gray,
                                    shape = RoundedCornerShape(5.dp)
                                )
                                .clip(RoundedCornerShape(5.dp))
                                .background(Color.White),
                        ) {
                            ClickableText(
                                AnnotatedString(viewModel.classs.value),
                                onClick = {
                                    type = CLASSES
                                    viewModel.getHardCodedDataFromFirebase(type)
                                    showDialogGenerals.value = true
                                },
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(10.dp)
                            )
                        }
                        Spacer(modifier = Modifier.padding(int_spacer))

                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Center,
                            modifier = Modifier
                                .height(60.dp)
                                .fillMaxWidth()
                                .border(
                                    width = 1.dp,
                                    color = Color.Gray,
                                    shape = RoundedCornerShape(5.dp)
                                )
                                .clip(RoundedCornerShape(5.dp))
                                .background(Color.White),
                        ) {
                            ClickableText(
                                AnnotatedString(viewModel.tier.value),
                                onClick = {
                                    type = TIERS
                                    Log.d("Main", "ScreenNewCharacher 1: " + type)
                                    viewModel.getHardCodedDataFromFirebase(type)
                                    showDialogGenerals.value = true
                                },
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(10.dp)
                            )
                        }
                        Spacer(modifier = Modifier.padding(int_spacer))

                        OutlinedTextField(
                            value = character_hp,
                            label = { Text(text = "character_hp") },
                            keyboardOptions =  KeyboardOptions(keyboardType = KeyboardType.Number, imeAction = ImeAction.Next),
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(Color.White),
                            onValueChange = { character_hp = it })

                        Spacer(modifier = Modifier.padding(int_spacer))

                        OutlinedTextField(
                            value = character_atk,
                            label = { Text(text = "character_atk") },
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number, imeAction = ImeAction.Next),
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(Color.White),

                            onValueChange = { character_atk = it })

                        Spacer(modifier = Modifier.padding(int_spacer))

                        OutlinedTextField(
                            value = character_def,
                            label = { Text(text = "character_def") },
                            keyboardOptions =  KeyboardOptions(keyboardType = KeyboardType.Number, imeAction = ImeAction.Next),
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

                        //listview con i nomi delle abilità

                        if (abilityListState.value.size > 0) {
                            for (i in 0..abilityListState.value.size - 1) {
                                Log.d("Main Alert", "PersonalizedAlertDialog: " + abilityListState.value.get(i).toString())



                                TextButton(
                                    modifier = Modifier
                                        .height(50.dp)
                                        .fillMaxWidth(),
                                    onClick = {

                                    },
                                    border = BorderStroke(2.dp, Color.Gray),
                                    shape = RoundedCornerShape(15.dp)
                                ) {
                                    Text(text = abilityListState.value.get(i).ability_name.toString(),)
                                }

                                Spacer(modifier = Modifier.padding(10.dp))
                            }
                        }

                        //----------------------------------------------- OK BUTTON ----------------------------------------------------------
                        TextButton(
                            modifier = Modifier
                                .height(50.dp)
                                .fillMaxWidth(),
                            onClick = {
                                //add firebase
                                val character = Character(
                                    character_name.text,
                                    viewModel.ability,
                                    viewModel.faction.value,
                                    character_description.text,
                                    viewModel.classs.value,
                                    viewModel.tier.value,
                                    character_atk.text,
                                    character_def.text,
                                    character_hp.text,
                                    character_avatar.text
                                )

                                Log.d(TAG, "Main ScreenNewCharacher: Added Character: " + character)
                                val firebase= Firebase()
                                firebase.addCharacterFirebase(character)
                            },
                            border = BorderStroke(2.dp, Color.Gray),
                            shape = RoundedCornerShape(15.dp)
                        ) {
                            Text(text = "OK", color = colorResource(id = R.color.black))
                        }

                    }
                }
            }
        )
    }


}




