package com.example.kamussaya.feature_kamus.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.kamussaya.feature_kamus.domain.models.Meaning
import com.example.kamussaya.feature_kamus.domain.models.Word

@Entity
data class WordEntity(
    val arti: List<Meaning>,
    val lema: String,
    val word: String,
    @PrimaryKey val id: Int? = null
) {
    fun toWord(): Word = Word(arti, lema, word = word)
}
