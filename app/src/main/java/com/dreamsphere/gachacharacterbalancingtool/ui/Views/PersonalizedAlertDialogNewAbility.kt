package com.dreamsphere.gachacharacterbalancingtool.ui.Views
import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Popup
import androidx.compose.ui.window.PopupProperties
import com.dreamsphere.gachacharacterbalancingtool.Models.Objects.Ability
import com.dreamsphere.gachacharacterbalancingtool.R
import com.dreamsphere.gachacharacterbalancingtool.ViewModels.ViewModel
import kotlinx.coroutines.launch

@Composable
fun PersonalizedAlertDialogNewAbility(closeRecord: () -> Unit, viewModel: ViewModel) {

    val scrollState = rememberScrollState()
    var showDialogNewAbilityEffect = remember { mutableStateOf(false) }
    val scope = rememberCoroutineScope()
    val lazyListState = rememberLazyListState()
    val abilityEffectsListState = viewModel.abilityEffectsListFlow.collectAsState()

    //val generalsListState = viewModel.generalListFlow.collectAsState()

    var ability_name by remember { mutableStateOf(TextFieldValue("")) }
    var ability_cooldown by remember { mutableStateOf(TextFieldValue("")) }
    var ability_cost by remember { mutableStateOf(TextFieldValue("")) }
    var ability_unlock by remember { mutableStateOf(TextFieldValue("")) }
    var ability_change_aoe by remember { mutableStateOf(TextFieldValue("")) }
    var ability_effects by remember { mutableStateOf(TextFieldValue("")) }
    var ability_aoe by remember {mutableStateOf(false)}

    Surface(
        border = BorderStroke(1.dp, colorResource(id = R.color.black)),
        shape = RoundedCornerShape(8.dp),
        color = Color(0xEBEEEEEE),
        modifier = Modifier.padding(horizontal = 30.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(24.dp)
                .verticalScroll(
                    state = scrollState,

                    ),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            var titleText by rememberSaveable { mutableStateOf("") }
            Column(
                modifier = Modifier.fillMaxWidth(),

            ) {

                if (showDialogNewAbilityEffect.value){
                    Popup(
                        popupPositionProvider = WindowCenterOffsetPositionProvider(),
                        onDismissRequest = { showDialogNewAbilityEffect.value = false },
                        properties = PopupProperties(focusable = true)
                    ) {
                        PersonalizedAlertDialogNewAbilityEffect({
                            showDialogNewAbilityEffect.value = false
                            scope.launch {
                                lazyListState.scrollToItem(abilityEffectsListState.value.size)
                            }
                        }, viewModel)
                    }
                }

                OutlinedTextField(
                    value = ability_name,
                    label = { Text(text = "ability_name") },
                    keyboardOptions = KeyboardOptions( imeAction = ImeAction.Next),
                    modifier = Modifier
                        .fillMaxWidth(),
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        backgroundColor = Color.White),
                    onValueChange = { ability_name = it })
                Spacer(modifier = Modifier.padding(10.dp))

                OutlinedTextField(
                    value = ability_cooldown,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number, imeAction = ImeAction.Next),
                    label = { Text(text = "ability_cooldown") },
                    modifier = Modifier
                        .fillMaxWidth(),
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        backgroundColor = Color.White),
                    onValueChange = { ability_cooldown = it })
                Spacer(modifier = Modifier.padding(10.dp))

                OutlinedTextField(
                    value = ability_cost,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number, imeAction = ImeAction.Next),
                    label = { Text(text = "ability_cost") },
                    modifier = Modifier
                        .fillMaxWidth(),
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        backgroundColor = Color.White),
                    onValueChange = { ability_cost = it })
                Spacer(modifier = Modifier.padding(10.dp))

                OutlinedTextField(
                    value = ability_unlock,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number, imeAction = ImeAction.Next),
                    label = { Text(text = "ability_unlock") },
                    modifier = Modifier
                        .fillMaxWidth(),
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        backgroundColor = Color.White),
                    onValueChange = { ability_unlock = it })
                Spacer(modifier = Modifier.padding(10.dp))

                OutlinedTextField(
                    value = ability_change_aoe,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number, imeAction = ImeAction.Next),
                    label = { Text(text = "ability_change_aoe") },
                    modifier = Modifier
                        .fillMaxWidth(),
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        backgroundColor = Color.White),
                    onValueChange = { ability_change_aoe = it })
                Spacer(modifier = Modifier.padding(10.dp))

                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center, verticalAlignment = Alignment.CenterVertically) {

                    Text(text = "Ability AOE")
                    Switch(
                        checked = ability_aoe,
                        onCheckedChange = { switchOn_ ->
                            ability_aoe = switchOn_
                        }
                    )

                }

                if (abilityEffectsListState.value.size>0){
                    for (i in 0..abilityEffectsListState.value.size-1){
                        Log.d("Main Alert", "PersonalizedAlertDialog: "+abilityEffectsListState.value.get(i).toString())

                        TextButton(
                            modifier = Modifier
                                .height(50.dp)
                                .fillMaxWidth(),
                            onClick = {

                            },
                            border = BorderStroke(2.dp, Color.Gray),
                            shape = RoundedCornerShape(15.dp)
                        ) {
                            Text(text = abilityEffectsListState.value.get(i).ability_effect_name.toString(), color = colorResource(id = R.color.black))
                        }

                        Spacer(modifier = Modifier.padding(10.dp))
                    }
                }

                // ------------------------------------ OK BUTTON ----------------------------------------
                Row(modifier = Modifier.fillMaxWidth()) {
                    TextButton(
                        modifier = Modifier
                            .height(50.dp),
                        onClick = {
                            showDialogNewAbilityEffect.value= true
                        },
                        border = BorderStroke(2.dp, Color.Gray),
                        shape = RoundedCornerShape(15.dp)
                    ) {
                        Text(text = "Add Effect", color = colorResource(id = R.color.black))
                    }

                    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End) {
                        TextButton(
                            modifier = Modifier
                                .height(50.dp),

                            onClick = {
                                //val index = viewModel.ability.size
                                viewModel.ability.add(Ability(
                                    ability_name.text,
                                    viewModel.abilityEffectsListFlow.value,
                                    ability_cooldown.text.trim().toInt(),
                                    ability_cost.text.trim().toInt(),
                                    ability_unlock.text.trim().toInt(),
                                    ability_aoe,
                                    ability_change_aoe.text.trim().toInt(),



                                ))
                                closeRecord.invoke()
                                //add to list
                            },
                            border = BorderStroke(2.dp, Color.Gray),
                            shape = RoundedCornerShape(15.dp)
                        ) {
                            Text(text = "OK", color = colorResource(id = R.color.black))
                        }
                    }


                }



            }

        }
    }
}

