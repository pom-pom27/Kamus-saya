package com.example.kamussaya.feature_kamus.presentation

import com.example.kamussaya.feature_kamus.domain.models.Word

data class WordState(
    val wordInfoItems: List<Word> = emptyList(),
    val isLoading: Boolean = false
)
