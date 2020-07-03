package com.korzhueva.pokeapiclient.models

import com.squareup.moshi.Json

data class AbilityItem (
    val ability: PokemonAbility,
    @Json(name = "is_hidden") val isHidden: Boolean,
    val slot: Int
)