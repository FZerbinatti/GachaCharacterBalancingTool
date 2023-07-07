package com.dreamsphere.gachacharacterbalancingtool.ViewModels

import android.util.Log
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.dreamsphere.gachacharacterbalancingtool.Models.Objects.Ability
import com.dreamsphere.gachacharacterbalancingtool.Models.Objects.AbilityEffect
import com.dreamsphere.gachacharacterbalancingtool.Models.Objects.Character
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class ViewModel()  : ViewModel(){
    private val TAG = "Main ViewModel"

    //Generals
    private var generalList = mutableStateListOf<String>()
    private val _generalListFlow = MutableStateFlow(generalList)
    val generalListFlow: StateFlow<List<String>> get() = _generalListFlow
    //Characters
    var charactersList = mutableStateListOf<Character>()
    val _charactersListFlow = MutableStateFlow(charactersList)
    val charactersListFlow: StateFlow<List<Character>> get() = _charactersListFlow

    //index to point list
    var index = -1
    private val _indexviewState = MutableStateFlow<Int>(index)
    val indexviewState: StateFlow<Int> = _indexviewState




    var character= Character()
    var _characterViewState = MutableStateFlow<Character>(character)
    var characterViewState: StateFlow<Character> = _characterViewState

    // state of selected character data for other screeen
    val faction = mutableStateOf("select faction")
    val classs = mutableStateOf("select class")
    val tier = mutableStateOf("select tier")
    var character_name = mutableStateOf<String>("Character Name")

    //abilitiers specs
    val ability_effects = mutableStateListOf<AbilityEffect>()
    val ability= mutableStateListOf<Ability>()

    private val _abilityEffectsListFlow = MutableStateFlow(ability_effects)
    private val _abilityListFlow = MutableStateFlow(ability)

    val abilityEffectsListFlow: StateFlow<List<AbilityEffect>> get() = _abilityEffectsListFlow
    val abilityListFlow: StateFlow<List<Ability>> get() = _abilityListFlow





    fun selectCharacter(index: Int){
        if(!charactersList.isEmpty()){
            _characterViewState.value = charactersList[index]
        }
    }


    init {
        //getHardCodedDataFromFirebase(type)
        getCharactersFromFirebase()
        Log.d(TAG, "char? : "+characterViewState.value)

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

    fun getCharactersFromFirebase() {

        val database = com.google.firebase.ktx.Firebase.database("https://gachacharacterbalancingtool-default-rtdb.europe-west1.firebasedatabase.app/")
        //var index=0
        val firebase_shoplists_ids = database.getReference("characters")

        firebase_shoplists_ids.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                charactersList.clear()
                for (DataSnap in snapshot.children) {
                    val item = DataSnap.getValue(Character::class.java)

                    if (item != null) {
                        charactersList.add(item)

                        Log.d(TAG, "onDataChange: "+item)
                    }
                }
                Log.d(TAG, "getCharactersFromFirebase: "+charactersList.size)

            }
            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })
    }

/*    fun getCharecterData(characterName: String) {

        Log.d(TAG, "getCharactersFromFirebase: ")

        val database = com.google.firebase.ktx.Firebase.database("https://gachacharacterbalancingtool-default-rtdb.europe-west1.firebasedatabase.app/")
        //var index=0
        val firebase_shoplists_ids = database.getReference("characters").child(characterName)

        firebase_shoplists_ids.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                generalList.clear()
                    val item = snapshot.getValue(Character::class.java)
                _characterViewState.value= item!!
                Log.d(TAG, "onDataChange ================== : "+item.character_name)

            }
            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })

    }*/
}