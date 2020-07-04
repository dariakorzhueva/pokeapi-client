package com.korzhueva.pokeapiclient.models

import com.squareup.moshi.Json

data class PokemonResponse(
    //val abilities: List<AbilityItem>,
    @Json(name = "base_experience")val baseExperience: Int,
    val forms: List<PokemonForm>,
    val height: Int,
    val id: Int,
    @Json(name = "is_default")val isDefault: Boolean,
    @Json(name="location_area_encounters") val locationAreaEncounters: String,
    val name: String,
    val order: Int,
    val species: PokemonSpecies,
    val sprites: PokemonSprite,
    val stats: List<StatsItem>,
    val types: List<TypeItem>,
    val weight: Int
)