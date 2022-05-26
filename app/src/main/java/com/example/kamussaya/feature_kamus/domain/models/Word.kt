package com.example.kamussaya.feature_kamus.domain.models

data class Word(
    val arti: List<Meaning>,
    val lema: String,
    val word: String
)
