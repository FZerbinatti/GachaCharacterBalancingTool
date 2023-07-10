package com.dreamsphere.gachacharacterbalancingtool.Models.Objects

data class Character(

    var character_name: String?="",
    val character_abilities_list: List<Ability>?=null,
    var character_faction: String?="",
    val character_description:String?="",
    var character_class:String?="",
    var character_tier:String?="",
    val character_atk:String?="",
    val character_def:String?="",
    val character_hp:String?="",
    val character_avatar:String?="",

    )
