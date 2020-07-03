package com.korzhueva.pokeapiclient.models

import com.squareup.moshi.Json

data class StatsItem(
    @Json(name="base_stat") val baseStat: Int,
    val effort: Int,
    val stat: PokemonStat
)
