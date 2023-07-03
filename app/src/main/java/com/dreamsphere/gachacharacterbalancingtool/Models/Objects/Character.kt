package com.dreamsphere.gachacharacterbalancingtool.Models.Objects

data class Character(

    val character_name: String,
    val character_abilities_list: List<Ability>,
    val character_faction: String,
    val character_description:String,
    val character_class:String,
    val character_tier:String,
    val character_atk:String,
    val character_def:String,
    val character_hp:String,
    val character_avatar:String,


)
