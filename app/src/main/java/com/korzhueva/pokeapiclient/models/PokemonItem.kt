package com.korzhueva.pokeapiclient.models

data class PokemonItem(
    val id: String = "",
    val name: String  = "",
    var type: String  = "",
    var height: Int = 0,
    var weight: Int = 0,
    var attack: Int = 0,
    var defense: Int = 0,
    var hp: Int = 0,
    var sprite: String? = ""
)