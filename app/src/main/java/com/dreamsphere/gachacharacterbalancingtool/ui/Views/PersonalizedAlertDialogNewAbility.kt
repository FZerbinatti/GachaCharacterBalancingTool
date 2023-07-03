package com.dreamsphere.gachacharacterbalancingtool.ui.Views
import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import com.dreamsphere.gachacharacterbalancingtool.R
import com.dreamsphere.gachacharacterbalancingtool.ViewModels.ViewModel

@Composable
fun PersonalizedAlertDialogNewAbility(closeRecord: () -> Unit, viewModel: ViewModel) {

    var ability_name by remember { mutableStateOf(TextFieldValue("")) }


    Surface(
        border = BorderStroke(1.dp, colorResource(id = R.color.black)),
        shape = RoundedCornerShape(8.dp),
        color = Color(0xEBEEEEEE),
        modifier = Modifier.padding(horizontal = 30.dp)
    ) {
        Column(
            modifier = Modifier.padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            var titleText by rememberSaveable { mutableStateOf("") }
            Column(
                modifier = Modifier.fillMaxWidth(),

            ) {
                OutlinedTextField(
                    value = ability_name,
                    label = { Text(text = "ability_name") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.White),
                    onValueChange = { ability_name = it })

                Spacer(modifier = Modifier.padding(10.dp))


                TextButton(
                    modifier = Modifier
                        .height(50.dp)
                        .fillMaxWidth(),
                    onClick = {
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

