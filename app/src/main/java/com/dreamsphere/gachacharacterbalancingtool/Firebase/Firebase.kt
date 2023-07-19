package com.dreamsphere.sharedshoplistk.repository.Firebase

import android.content.Context
import android.content.res.Resources
import android.util.Log
import com.dreamsphere.gachacharacterbalancingtool.Models.Objects.Character
import com.dreamsphere.gachacharacterbalancingtool.Models.Objects.Classs
import com.dreamsphere.gachacharacterbalancingtool.R
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class Firebase(context: Context) {

    private val TAG = "Main Firebase"

    val database = Firebase.database(context.getString(R.string.DATABASE))
    val database_characters = database.getReference(context.getString(R.string.CHARACTERS))
    val database_attributes = database.getReference(context.getString(R.string.ATTRIBUTES))
    val database_characters_attributes = (context.getString(R.string.CHARACTERS_ATTRIBUTES))
    val database_classes = (context.getString(R.string.CLASSES))


    fun addCharacterFirebase(character: Character) {
        Log.d(TAG, "addCharacterFirebase: "+character)
        database_characters.child(character.character_name.toString()).setValue(character)
    }

    fun addClasssFirebase(classs: Classs) {
        database_attributes
            .child(database_characters_attributes)
            .child(database_classes)
            .child(classs.classs_name.toString())
            .setValue(classs)
    }

    fun deleteFromFirebase(item_name: String) {
        database_characters.child(item_name).removeValue()
    }

}