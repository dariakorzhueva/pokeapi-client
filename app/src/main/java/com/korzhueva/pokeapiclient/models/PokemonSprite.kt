package com.korzhueva.pokeapiclient.models

import com.squareup.moshi.Json

data class PokemonSprite (
    @Json(name="back_default") val backDefault: String?,
    @Json(name="back_female") val backFemale: String?,
    @Json(name="back_shiny") val backShiny: String?,
    @Json(name="back_shiny_female") val backShinyFemale: String?,
    @Json(name="front_default") val frontDefault: String?,
    @Json(name="front_female") val frontFemale: String?,
    @Json(name="front_shiny") val frontShiny: String?,
    @Json(name="front_shiny_female") val frontShinyFemale: String?
)