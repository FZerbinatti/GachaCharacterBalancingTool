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
import com.dreamsphere.gachacharacterbalancingtool.Models.Objects.Ability
import com.dreamsphere.gachacharacterbalancingtool.Models.Objects.AbilityEffect
import com.dreamsphere.gachacharacterbalancingtool.R
import com.dreamsphere.gachacharacterbalancingtool.ViewModels.ViewModel

@Composable
fun PersonalizedAlertDialogNewAbilityEffect(closeRecord: () -> Unit, viewModel: ViewModel) {

    val scrollState = rememberScrollState()

    var ability_effect_name by remember { mutableStateOf(TextFieldValue("")) }
    var ability_effect_type by remember { mutableStateOf(TextFieldValue("")) }
    var ability_effect_value by remember { mutableStateOf(TextFieldValue("")) }
    var ability_effect_description by remember { mutableStateOf(TextFieldValue("")) }
    var ability_effect_unolock by remember { mutableStateOf(TextFieldValue("")) }

    Surface(
        border = BorderStroke(1.dp, colorResource(id = R.color.black)),
        shape = RoundedCornerShape(8.dp),
        color = Color(0xEBEEEEEE),
        modifier = Modifier.padding(horizontal = 30.dp)
    ) {
        Column(
            modifier = Modifier.padding(24.dp).verticalScroll(
                state = scrollState,

                ),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            var titleText by rememberSaveable { mutableStateOf("") }
            Column(
                modifier = Modifier.fillMaxWidth(),

            ) {
                OutlinedTextField(
                    value = ability_effect_name,
                    label = { Text(text = "ability_effect_name") },
                    keyboardOptions = KeyboardOptions( imeAction = ImeAction.Next),
                    modifier = Modifier
                        .fillMaxWidth(),
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        backgroundColor = Color.White),
                    onValueChange = { ability_effect_name = it })
                Spacer(modifier = Modifier.padding(10.dp))

                OutlinedTextField(
                    value = ability_effect_type,
                    label = { Text(text = "ability_effect_type") },
                    keyboardOptions = KeyboardOptions( imeAction = ImeAction.Next),
                    modifier = Modifier
                        .fillMaxWidth(),
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        backgroundColor = Color.White),
                    onValueChange = { ability_effect_type = it })
                Spacer(modifier = Modifier.padding(10.dp))

                OutlinedTextField(
                    value = ability_effect_value,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number, imeAction = ImeAction.Next),
                    label = { Text(text = "ability_effect_value") },
                    modifier = Modifier
                        .fillMaxWidth(),
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        backgroundColor = Color.White),
                    onValueChange = { ability_effect_value = it })
                Spacer(modifier = Modifier.padding(10.dp))

                OutlinedTextField(
                    value = ability_effect_description,

                    label = { Text(text = "ability_effect_description") },
                    keyboardOptions = KeyboardOptions( imeAction = ImeAction.Next),
                    modifier = Modifier
                        .fillMaxWidth(),
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        backgroundColor = Color.White),
                    onValueChange = { ability_effect_description = it })
                Spacer(modifier = Modifier.padding(10.dp))

                OutlinedTextField(
                    value = ability_effect_unolock,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number, imeAction = ImeAction.Next),
                    label = { Text(text = "ability_effect_unolock") },
                    modifier = Modifier
                        .fillMaxWidth(),
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        backgroundColor = Color.White),
                    onValueChange = { ability_effect_unolock = it })
                Spacer(modifier = Modifier.padding(10.dp))





                // ------------------------------------ OK BUTTON ----------------------------------------
                TextButton(
                    modifier = Modifier
                        .height(50.dp)
                        .fillMaxWidth(),
                    onClick = {
                        //val index = viewModel.ability_effects.size
                        viewModel.ability_effects.add(AbilityEffect(
                            ability_effect_name.text,
                            ability_effect_type.text,
                            ability_effect_value.text.trim().toInt(),
                            ability_effect_description.text,
                            ability_effect_unolock.text.trim().toInt()  ))
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

