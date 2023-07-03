package com.dreamsphere.gachacharacterbalancingtool.ViewModels

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class ViewModel()  : ViewModel(){
    private val TAG = "Main ViewModel"
    private var generalList = mutableStateListOf<String>()
    private val _generalListFlow = MutableStateFlow(generalList)
    val generalListFlow: StateFlow<List<String>> get() = _generalListFlow

    val faction = mutableStateOf("select faction")
    val classs = mutableStateOf("select class")
    val tier = mutableStateOf("select tier")

    init {
        //getHardCodedDataFromFirebase(type)
    }

    fun getHardCodedDataFromFirebase(type: String) {
        Log.d(TAG, "getHardCodedDataFromFirebase: "+type)

        val database = com.google.firebase.ktx.Firebase.database("https://gachacharacterbalancingtool-default-rtdb.europe-west1.firebasedatabase.app/")
        //var index=0
        val firebase_shoplists_ids = database.getReference("hardcoded_data").child("characters_general").child(type)

        firebase_shoplists_ids.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                generalList.clear()
                for (DataSnap in snapshot.children) {
                    //val item = DataSnap.getValue(String::class.java)
                    val item =DataSnap.key
                    if (item != null) {
                        generalList.add(item)
                        Log.d(TAG, "onDataChange: "+item)
                    }
                }
            }
            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })
    }
}