package com.dreamsphere.sharedshoplistk.repository.Firebase

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import com.dreamsphere.gachacharacterbalancingtool.Models.Objects.Character
import com.dreamsphere.gachacharacterbalancingtool.Models.Objects.Classs
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
        Log.d(TAG, "addCharacterFirebase: "+character)
        firebase_shoplists_ids.child(character.character_name.toString()).setValue(character)
    }

    fun addClasssFirebase(classs: Classs) {
        val firebase_shoplists_ids2 = database.getReference("hardcoded_data/characters_general/classes")
        firebase_shoplists_ids2.child(classs.classs_name.toString()).setValue(classs)
    }

    fun deleteFromFirebase(item_name: String) {
        firebase_shoplists_ids.child(item_name).removeValue()
    }


}