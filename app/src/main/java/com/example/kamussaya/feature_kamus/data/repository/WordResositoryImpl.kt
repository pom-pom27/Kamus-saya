package com.example.kamussaya.feature_kamus.data.repository

import android.util.Log
import com.example.kamussaya.core.util.Resource
import com.example.kamussaya.feature_kamus.data.local.WordDao
import com.example.kamussaya.feature_kamus.data.remote.KamusApi
import com.example.kamussaya.feature_kamus.domain.models.Word
import com.example.kamussaya.feature_kamus.domain.repository.WordsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException

class WordResositoryImpl(private val kamusApi: KamusApi, private val dao: WordDao) :
    WordsRepository {
    override fun getWords(word: String): Flow<Resource<List<Word>>> = flow {
        //trigger loading
        emit(Resource.Loading())

        //get data from db
        val wordsfromDb = dao.getWords(word).map { it.toWord() }

        try {
            //get data from api
            val remoteWords = kamusApi.getWordList(word).data

            //delete then add the new data
            dao.deleteWords(remoteWords.map { it.toWordEntity(word).word })
            dao.insertWord(remoteWords.map { it.toWordEntity(word) })

        } catch (e: HttpException) {
            //invalid response / word not found
            emit(Resource.Error(message = "Kata tidak ditemukan.", data = wordsfromDb))

        } catch (e: IOException) {
            //no internet /or unreachable server

            emit(
                Resource.Error(
                    message = "Tidak bisa menghubungi server. Cek koneksi internet anda",
                    data = wordsfromDb
                )
            )
        }
        val newWordInfos = dao.getWords(word).map { it.toWord() }

        Log.d("Adas", "getWords: $newWordInfos")


        emit(Resource.Success(data = newWordInfos))
    }

}
