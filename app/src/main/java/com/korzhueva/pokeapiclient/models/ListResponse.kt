package com.korzhueva.pokeapiclient.models

data class ListResponse (
    val count: Int,
    val next: String?,
    val previous: String?,
    val results: List<PokemonLink>
)
