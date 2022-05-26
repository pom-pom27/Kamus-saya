package com.example.kamussaya.feature_kamus.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.kamussaya.feature_kamus.data.local.entities.WordEntity

@Database(entities = [WordEntity::class], version = 1)
@TypeConverters(Converters::class)
abstract class KamusSayaDatabase : RoomDatabase() {

    abstract val dao: WordDao
}

/*
* Room flow:
* 1. create entity
* 2. create dao
* 3. create converter for list
*/
