package com.korzhueva.pokeapiclient.utils

import com.korzhueva.pokeapiclient.models.PokemonItem
import com.korzhueva.pokeapiclient.models.PokemonLink

fun PokemonLink.asDomainModel(): PokemonItem {
    val parts = url.split("/")
    val resId = (parts[parts.size-2])

    return PokemonItem(
        id = resId,
        name = name.capitalize()
    )
}