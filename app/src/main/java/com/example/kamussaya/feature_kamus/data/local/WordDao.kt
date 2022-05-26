package com.example.kamussaya.feature_kamus.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.kamussaya.feature_kamus.data.local.entities.WordEntity

@Dao
interface WordDao {

    //insert
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWord(words: List<WordEntity>)

    //delete words
    @Query("DELETE FROM wordentity WHERE word IN(:word)")
    suspend fun deleteWords(word: List<String>)

    //select item
    @Query("SELECT * FROM wordentity where word like :word ")
    suspend fun getWords(word: String): List<WordEntity>
}
