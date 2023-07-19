package com.dreamsphere.gachacharacterbalancingtool.ViewModels

import android.content.Context
import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.dreamsphere.gachacharacterbalancingtool.Models.Objects.Ability
import com.dreamsphere.gachacharacterbalancingtool.Models.Objects.AbilityEffect
import com.dreamsphere.gachacharacterbalancingtool.Models.Objects.Avatar
import com.dreamsphere.gachacharacterbalancingtool.Models.Objects.Character
import com.dreamsphere.gachacharacterbalancingtool.R
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class ViewModel(context: Context)  : ViewModel(){
    private val TAG = "Main ViewModel"

    //FIREBASE
    val database = Firebase.database(context.getString(R.string.DATABASE))
    val database_characters = database.getReference((context.getString(R.string.CHARACTERS)))
    val database_attributes = database.getReference((context.getString(R.string.ATTRIBUTES)))

    //Generals
    private var generalList = mutableStateListOf<String>()
    private val _generalListFlow = MutableStateFlow(generalList)
    val generalListFlow: StateFlow<List<String>> get() = _generalListFlow
    //Characters
    var charactersList = mutableStateListOf<Character>()
    val _charactersListFlow = MutableStateFlow(charactersList)
    val charactersListFlow: StateFlow<List<Character>> get() = _charactersListFlow

    // avatar selection
    private var avatarList = mutableStateListOf<Avatar>()
    private val _avatarListFlow = MutableStateFlow(avatarList)
    val avatarListFlow: StateFlow<List<Avatar>> get() = _avatarListFlow

    //index to point list
    var index = -1
    private val _indexviewState = MutableStateFlow<Int>(index)
    val indexviewState: StateFlow<Int> = _indexviewState




    var character= Character()
    var _characterViewState = MutableStateFlow<Character>(character)
    var characterViewState: StateFlow<Character> = _characterViewState

    // state of selected character data for other screeen

    val faction_string = "select faction"
    val classs_string = "select class"
    val tier_string = "select tier"
    var avatar_string = "avatar_name"

    val faction = mutableStateOf(faction_string)
    val classs = mutableStateOf(classs_string)
    val tier = mutableStateOf(tier_string)
    val avatar = mutableStateOf(avatar_string)

    //abilitiers specs
    val ability_effects = mutableStateListOf<AbilityEffect>()
    val ability= mutableStateListOf<Ability>()

    private val _abilityEffectsListFlow = MutableStateFlow(ability_effects)
    private val _abilityListFlow = MutableStateFlow(ability)

    val abilityEffectsListFlow: StateFlow<List<AbilityEffect>> get() = _abilityEffectsListFlow
    val abilityListFlow: StateFlow<List<Ability>> get() = _abilityListFlow

    fun selectCharacter(index: Int){
        /*if(!charactersList.isEmpty()){
            _characterViewState.value = charactersList[index]
            faction.value= charactersList[index].character_faction.toString()
        }*/
    }

    init {
        //getHardCodedDataFromFirebase(type)
        getCharactersFromFirebase()
        Log.d(TAG, "char? : "+characterViewState.value)
    }

    fun getHardCodedDataFromFirebase(type: String, context: Context) {
        Log.d(TAG, "getHardCodedDataFromFirebase: "+type)

        //var index=0
        val firebase_shoplists_ids = database_attributes.child((context.getString(R.string.CHARACTERS_ATTRIBUTES))).child(type)

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

        database_characters.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                charactersList.clear()

                for (DataSnap in snapshot.children) {
                    val item = DataSnap.getValue(Character::class.java)
                    Log.d(TAG, "onDataChange: item got? "+item)
                    if (item != null) {
                        charactersList.add(item)
                        if (item.character_abilities_list != null){
                            for (i in 0..item.character_abilities_list!!.size-1){
                                ability.add(item.character_abilities_list[i])
                                Log.d(TAG, "Main onDataChange: "+item.character_abilities_list[i])
                                if (item.character_abilities_list[i].ability_effects != null){
                                    Log.d(TAG, "Main onDataChange: "+item.character_abilities_list[i].ability_effects!!.size)
                                    Log.d(TAG, "Main onDataChange: "+item.character_abilities_list[i].ability_effects!!)

                                    for (j in 0 ..item.character_abilities_list[i].ability_effects!!.size-1){
                                        ability_effects.add(item.character_abilities_list[i].ability_effects!![j])
                                    }
                                }

                            }
                        }


                        Log.d(TAG, "onDataChange: "+item)
                    }
                    ability.clear()
                    ability_effects.clear()

                }
                Log.d(TAG, "getCharactersFromFirebase: "+charactersList.size)


            }
            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
    }

    fun getAvatarsFromFirebase() {
        Log.d(TAG, "getAvatarsFromFirebase: ")
        //var storageRef = storage.reference
        //val storage = FirebaseStorage.getInstance("gs://gachacharacterbalancingtool.appspot.com")
        val storage = FirebaseStorage.getInstance()
       // val storage_reference = storage.reference.child("avatars/")
        var storageRef = storage.reference

        val gsReference = storage.getReferenceFromUrl("gs://gachacharacterbalancingtool.appspot.com/avatars")
        Log.d(TAG, "getAvatarsFromFirebase: "+gsReference)
        avatarList.clear()
        gsReference.listAll()
            .addOnSuccessListener { listResult ->
                for (item in listResult.items) {
                    avatarList.add(Avatar( item.name, gsReference.toString()+"/"+item.name))
                    Log.d(TAG, "getAvatarsFromFirebase: "+  item.name + " path: "+gsReference.toString()+"/"+item.name)

                }
            }
            .addOnFailureListener { exception ->
                // Handle any errors
                Log.d(TAG, "getAvatarsFromFirebase Error: "+exception)
            }
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