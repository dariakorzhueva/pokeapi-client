package com.korzhueva.pokeapiclient.models

data class PokemonItem(
    val id: Int,
    val name: String,
    val type: String,
    val height: Int,
    val weight: Int,
    val attack: Int,
    val defense: Int,
    val hp: Int,
    val sprite: String
)