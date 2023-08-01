package com.jaidis.jetpack.rickmortyapi.data

data class GsonInfo(
    val count: Number?,
    val pages: Number?,
    val next: String?,
    val prev: String?,
)

fun GsonInfo.asInfo(): Info {
    return Info(
        count = count,
        pages = pages,
        next = next,
        prev = next
    )
}