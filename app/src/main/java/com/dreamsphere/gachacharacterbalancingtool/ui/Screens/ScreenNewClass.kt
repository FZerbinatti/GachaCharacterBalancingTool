package com.dreamsphere.gachacharacterbalancingtool.ui.Screens

import android.annotation.SuppressLint
import android.content.ContentValues.TAG
import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
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
import com.dreamsphere.gachacharacterbalancingtool.Models.Objects.Classs
import com.dreamsphere.gachacharacterbalancingtool.R
import com.dreamsphere.gachacharacterbalancingtool.ViewModels.ViewModel
import com.dreamsphere.gachacharacterbalancingtool.ui.NavigationTools.Screen
import com.dreamsphere.gachacharacterbalancingtool.ui.Views.PersonalizedAlertDialogAvatars
import com.dreamsphere.gachacharacterbalancingtool.ui.Views.PersonalizedAlertDialogGenerals
import com.dreamsphere.gachacharacterbalancingtool.ui.Views.PersonalizedAlertDialogNewAbility
import com.dreamsphere.gachacharacterbalancingtool.ui.Views.WindowCenterOffsetPositionProvider
import com.dreamsphere.gachacharacterbalancingtool.ui.theme.GachaCharacterBalancingToolTheme
import com.dreamsphere.sharedshoplistk.repository.Firebase.Firebase
import kotlinx.coroutines.launch



@OptIn(ExperimentalComposeUiApi::class)
@SuppressLint("UnusedMaterialScaffoldPaddingParameter", "UnrememberedMutableState")
@Composable
fun ScreenNewClass(navController: NavController) {

    val context = LocalContext.current
    val int_spacer = 10.dp
    val scrollState = rememberScrollState()

    //var current_character = viewModel.characterViewState.value

    var cartello = mutableStateOf<String>("Creazione nuova classe")


    var class_name by remember { mutableStateOf(TextFieldValue("")) }


    var class_description by remember { mutableStateOf(TextFieldValue("")) }

    Log.d(TAG, "Main ScreenNewClass: ")


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
                        Text(text = cartello.value)

                        // ------------------------------------- boxes --------------------------------------------------
                        OutlinedTextField(
                            value = class_name,
                            label = { Text(text = "class_name_name") },
                            onValueChange = { class_name =it },

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

                        OutlinedTextField(
                            value = class_description,
                            label = { Text(text = "character_description") },
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(Color.White),
                            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),

                            onValueChange = {class_description = it })
                        Spacer(modifier = Modifier.padding(int_spacer))




                        Spacer(modifier = Modifier.padding(int_spacer))

                        TextButton(
                            modifier = Modifier
                                .height(50.dp)
                                .fillMaxWidth(),
                            onClick = {
                                val classs = Classs(
                                    class_name.text,
                                    class_description.text,
                                )


                                if (class_name.text.isEmpty()
                                    ||class_description.text.isEmpty()
                                    ||class_name.text.equals("")
                                    ||class_description.text.equals("")){


                                    cartello.value= "compila i campi"


                                }else{
                                    val firebase= Firebase(context)
                                    firebase.addClasssFirebase(classs)
                                    navController.navigate(Screen.ScreenMainMenu.route)
                                }
                            },
                            border = BorderStroke(2.dp, Color.Gray),
                            shape = RoundedCornerShape(15.dp)
                        ) {
                            Text(text = "Aggiungi Classe", color = colorResource(id = R.color.black))
                        }

}


                    }











