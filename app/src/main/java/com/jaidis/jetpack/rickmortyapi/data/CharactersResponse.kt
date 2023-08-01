package com.jaidis.jetpack.rickmortyapi.data

data class CharactersResponse(
    val info: GsonInfo,
    val results: List<GsonCharacter>
)

fun CharactersResponse.asCharacters(): List<Character> {
    return results.map {
        it.asCharacter()
    }
}