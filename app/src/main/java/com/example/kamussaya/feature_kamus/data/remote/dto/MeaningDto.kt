package com.example.kamussaya.feature_kamus.data.remote.dto

import com.example.kamussaya.feature_kamus.domain.models.Meaning

data class MeaningDto(
    val deskripsi: String,
    val kelas_kata: String
) {
    fun toMeaning(): Meaning = Meaning(deskripsi, kelas_kata)
}
