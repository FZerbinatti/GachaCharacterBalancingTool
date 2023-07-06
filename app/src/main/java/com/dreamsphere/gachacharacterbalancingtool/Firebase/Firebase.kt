package com.dreamsphere.sharedshoplistk.repository.Firebase

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import com.dreamsphere.gachacharacterbalancingtool.Models.Objects.Character
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import kotlin.math.log

class Firebase() {


    private val TAG = "Main Firebase"


    val database =
        Firebase.database("https://gachacharacterbalancingtool-default-rtdb.europe-west1.firebasedatabase.app/")

    val firebase_shoplists_ids = database.getReference("characters")






    fun addCharacterFirebase(character: Character) {
        firebase_shoplists_ids.child(character.character_name.toString()).setValue(character)
    }

    fun deleteFromFirebase(item_name: String) {
        firebase_shoplists_ids.child(item_name).removeValue()
    }


}