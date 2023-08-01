package com.jaidis.jetpack.rickmortyapi.data

data class CharacterResponse(
    val character: GsonCharacter
)

fun CharacterResponse.asCharacter(): Character {
    return character.asCharacter()
}