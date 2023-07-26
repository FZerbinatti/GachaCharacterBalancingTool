package com.dreamsphere.gachacharacterbalancingtool.ui.Views
import android.util.Log
import androidx.compose.foundation.BorderStroke
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
import androidx.compose.ui.unit.dp
import com.dreamsphere.gachacharacterbalancingtool.R
import com.dreamsphere.gachacharacterbalancingtool.ViewModels.ViewModel

@Composable
fun PersonalizedAlertDialogGenerals(
    closeRecord: () -> Unit,
    viewModel: ViewModel,
    type: String,
    index: String?
) {


    val generalsListState = viewModel.generalListFlow.collectAsState()
    val lazyListState = rememberLazyListState()
    val scope = rememberCoroutineScope()

    val FACTIONS = "FACTIONS"
    val CLASSES = "CLASSES"
    val TIERS = "TIERS"
    /*val focusRequester = remember { FocusRequester() }

    LaunchedEffect(focusRequester) {
        awaitFrame()
        focusRequester.requestFocus()
    }*/

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
                Log.d("Main Alert", "PersonalizedAlertDialog: "+generalsListState.value.size)
                Log.d("Main Alert", "PersonalizedAlertDialog: "+generalsListState.value.toString())
                if (generalsListState.value.size>0){


                    for (i in 0..generalsListState.value.size-1){
                        Log.d("Main Alert", "PersonalizedAlertDialog: "+generalsListState.value.get(i).toString())

                        TextButton(
                            modifier = Modifier
                                .height(50.dp)
                                .fillMaxWidth(),
                            onClick = {
                                if (type.equals(FACTIONS)){
                                    viewModel.faction.value= generalsListState.value.get(i)
                                    if (viewModel.charactersListFlow.value.size>0&& index!!.toInt()>-1){
                                        viewModel.charactersListFlow.value[index!!.toInt()].character_faction=generalsListState.value.get(i)
                                    }
                                }else
                                if (type.equals(CLASSES)){
                                    viewModel.classs.value= generalsListState.value.get(i)
                                    if (viewModel.charactersListFlow.value.size>0&& index!!.toInt()>-1){
                                        viewModel.charactersListFlow.value[index!!.toInt()].character_class=generalsListState.value.get(i)
                                    }
                                }else
                                if (type.equals(TIERS)){
                                    viewModel.tier.value= generalsListState.value.get(i)
                                    if (viewModel.charactersListFlow.value.size>0&& index!!.toInt()>-1){
                                        viewModel.charactersListFlow.value[index!!.toInt()].character_tier=generalsListState.value.get(i)
                                    }
                                }


                                closeRecord.invoke()

                            },
                            border = BorderStroke(2.dp, Color.Gray),
                            shape = RoundedCornerShape(15.dp)
                        ) {
                            Text(text = generalsListState.value.get(i), color = colorResource(id = R.color.black))
                        }



                        Spacer(modifier = Modifier.padding(10.dp))
                    }
                }


            }

        }
    }
}

