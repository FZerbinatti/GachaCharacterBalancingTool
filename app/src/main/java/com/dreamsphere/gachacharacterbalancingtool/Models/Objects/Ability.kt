package com.dreamsphere.gachacharacterbalancingtool.Models.Objects

data class Ability(

    val ability_name: String?=null,
    val ability_effects: List<AbilityEffect>?=null,
    val ability_cooldown: Int?=null,
    val ability_cost: Int?=null,
    val ability_unlock: Int?=null,
    val ability_aoe: Boolean?=null,
    val ability_change_aoe: Int?=null,

)
