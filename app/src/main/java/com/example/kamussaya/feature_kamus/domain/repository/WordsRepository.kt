package com.example.kamussaya.feature_kamus.domain.repository

import com.example.kamussaya.core.util.Resource
import com.example.kamussaya.feature_kamus.domain.models.Word
import kotlinx.coroutines.flow.Flow

interface WordsRepository {
    fun getWords(word: String): Flow<Resource<List<Word>>>
}
