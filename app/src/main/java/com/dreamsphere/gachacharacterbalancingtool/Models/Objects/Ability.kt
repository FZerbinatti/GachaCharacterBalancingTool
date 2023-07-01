package com.dreamsphere.gachacharacterbalancingtool.Models.Objects

data class Ability(

    val ability_name: String,
    val ability_effects: List<AbilityEffect>,
    val ability_cooldown: Int,
    val ability_cost: Int,
    val ability_unlock: Int,
    val ability_aoe: Boolean,
    val ability_change_aoe: Int

)
