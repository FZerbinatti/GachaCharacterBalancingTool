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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
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
import androidx.compose.ui.graphics.Color
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
@SuppressLint("UnusedMaterialScaffoldPaddingParameter", "UnrememberedMutableState")
@Composable
fun ScreenNewCharacher(navController: NavController, viewModel: ViewModel, index: String?) {


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

    var abilityListState = viewModel.abilityListFlow.collectAsState()
    val focusManager = LocalFocusManager.current

    var index_ability =-1

    //firebase
    val FACTIONS = "factions"
    val CLASSES = "classes"
    val TIERS = "tiers"



    //var current_character = viewModel.characterViewState.value

    var character_name_string = mutableStateOf<String>("String")
    var character_name by remember { mutableStateOf(TextFieldValue(character_name_string.toString())) }

    var character_description_string = mutableStateOf<String>("String")
    var character_description by remember { mutableStateOf(TextFieldValue(character_description_string.toString())) }

    var character_hp_string = mutableStateOf<String>("String")
    var character_hp by remember { mutableStateOf(TextFieldValue(character_hp_string.toString())) }

    var character_atk_string = mutableStateOf<String>("String")
    var character_atk by remember { mutableStateOf(TextFieldValue(character_atk_string.toString())) }

    var character_def_string = mutableStateOf<String>("String")
    var character_def by remember { mutableStateOf(TextFieldValue(character_def_string.toString())) }

    var character_avatar_string = mutableStateOf<String>("String")
    var character_avatar by remember { mutableStateOf(TextFieldValue(character_avatar_string.toString())) }

    var character_tier_string = mutableStateOf<String>("")
    character_tier_string.value = viewModel.tier.value

    var character_class_string = mutableStateOf<String>("")
    character_class_string.value = viewModel.classs.value

    var character_faction_string = mutableStateOf<String>("")
    val factionState = viewModel.faction_state.collectAsState()
    character_faction_string.value = factionState.value

    val characterListState = viewModel.charactersListFlow.collectAsState()

    Log.d(TAG, "Main ScreenNewCharacher: list size?  "+characterListState.value.size)
    Log.d(TAG, "Main ScreenNewCharacher: list index?  "+index)

    if (characterListState.value.isNotEmpty() && !index!!.equals("-1")){

        val char = characterListState.value[index?.toInt()!!]
        character_name_string.value = char.character_name.toString()
        character_description_string.value = char.character_description.toString()
        character_hp_string.value = char.character_hp.toString()
        character_atk_string.value = char.character_atk.toString()
        character_def_string.value = char.character_def.toString()
        character_avatar_string.value = char.character_avatar.toString()
        character_faction_string.value = char.character_faction.toString()
        character_tier_string.value = char.character_tier.toString()
        character_class_string.value = char.character_class.toString()


    }

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
                                }, viewModel, type, index)
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
                            value = character_name_string.value,
                            label = { Text(text = "character_name") },
                            onValueChange = { character_name_string.value =it },

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
                                AnnotatedString(character_faction_string.value),
                                onClick = {
                                    type = FACTIONS
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
                            value = character_description_string.value,
                            label = { Text(text = "character_description") },
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(Color.White),
                            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),

                            onValueChange = { character_description_string.value = it })
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
                                AnnotatedString(character_class_string.value),
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
                                AnnotatedString(character_tier_string.value),
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
                            value = character_hp_string.value,
                            label = { Text(text = "character_hp") },
                            keyboardOptions =  KeyboardOptions(keyboardType = KeyboardType.Number, imeAction = ImeAction.Next),
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(Color.White),
                            onValueChange = { character_hp_string.value = it })

                        Spacer(modifier = Modifier.padding(int_spacer))

                        OutlinedTextField(
                            value = character_atk_string.value,
                            label = { Text(text = "character_atk") },
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number, imeAction = ImeAction.Next),
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(Color.White),

                            onValueChange = { character_atk_string.value = it })

                        Spacer(modifier = Modifier.padding(int_spacer))

                        OutlinedTextField(
                            value = character_def_string.value,
                            label = { Text(text = "character_def") },
                            keyboardOptions =  KeyboardOptions(keyboardType = KeyboardType.Number, imeAction = ImeAction.Next),
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(Color.White),
                            onValueChange = { character_def_string.value = it })
                        Spacer(modifier = Modifier.padding(int_spacer))

                        Row(modifier = Modifier.fillMaxWidth()) {
                            OutlinedTextField(
                                value = character_avatar_string.value,
                                label = { Text(text = "character_avatar") },
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .background(Color.White),
                                onValueChange = { character_avatar_string.value = it })
                            TextButton(
                                modifier = Modifier
                                    .height(50.dp)
                                    .width(50.dp),
                                onClick = {
                                    // alert dialog with list of database avatar images
                                },
                                border = BorderStroke(2.dp, Color.Gray),
                                shape = RoundedCornerShape(15.dp)
                            ) {
                                Text(text = "+")
                            }
                        }


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
                                        // go to ability page and update viewmodel with differences
                                        Log.d(TAG, "ScreenNewCharacher: modify ability: "+abilityListState.value[i].toString())
                                        //navController.navigate(Screen.ScreenCharacterAbility.withArgs(abilityListState.value[i].toString()))
                                        //remember locally what ability (index list you are modifying
                                        //index_ability=i
                                        //showDialogNewAbility.value = true
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
                                    character_name_string.value,
                                    viewModel.ability,
                                    character_faction_string.value,
                                    character_description_string.value,
                                    character_class_string.value,
                                    character_tier_string.value,
                                    character_atk_string.value,
                                    character_def_string.value,
                                    character_hp_string.value,
                                    character_avatar_string.value
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




