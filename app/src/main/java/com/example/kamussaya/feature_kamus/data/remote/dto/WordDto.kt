package com.example.kamussaya.feature_kamus.data.remote.dto

import com.example.kamussaya.feature_kamus.data.local.entities.WordEntity

data class WordDto(
    val arti: List<MeaningDto>,
    val lema: String
) {
    fun toWordEntity(word: String): WordEntity =
        WordEntity(arti = arti.map { it.toMeaning() }, lema = lema, word = word)

}
