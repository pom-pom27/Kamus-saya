package com.example.kamussaya.feature_kamus.domain.use_case

import com.example.kamussaya.core.util.Resource
import com.example.kamussaya.feature_kamus.domain.models.Word
import com.example.kamussaya.feature_kamus.domain.repository.WordsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetWord(private val repository: WordsRepository) {

    operator fun invoke(word: String): Flow<Resource<List<Word>>> {
        if (word.isBlank()) {
            return flow {
                emit(Resource.Success(data = emptyList()))
            }
        }

        return repository.getWords(word)
    }
}
