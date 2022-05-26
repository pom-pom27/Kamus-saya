package com.example.kamussaya.feature_kamus.di

import android.app.Application
import androidx.room.Room
import com.example.kamussaya.feature_kamus.data.local.Converters
import com.example.kamussaya.feature_kamus.data.local.KamusSayaDatabase
import com.example.kamussaya.feature_kamus.data.remote.KamusApi
import com.example.kamussaya.feature_kamus.data.repository.WordResositoryImpl
import com.example.kamussaya.feature_kamus.data.util.GsonParser
import com.example.kamussaya.feature_kamus.domain.repository.WordsRepository
import com.example.kamussaya.feature_kamus.domain.use_case.GetWord
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class WordModule {

    @Provides
    @Singleton
    fun provideGetWordInfoUseCase(repository: WordsRepository): GetWord =
        GetWord(repository)

    @Provides
    @Singleton
    fun provideWordInfoRepository(
        dictionaryApi: KamusApi,
        db: KamusSayaDatabase
    ): WordsRepository =
        WordResositoryImpl(dictionaryApi, db.dao)

    @Provides
    @Singleton
    fun provideDictionaryApi(): KamusApi = Retrofit.Builder()
        .baseUrl(KamusApi.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(KamusApi::class.java)

    //? change converter here
    @Provides
    @Singleton
    fun provideWordInfoDatabase(app: Application): KamusSayaDatabase = Room.databaseBuilder(
        app, KamusSayaDatabase::class.java, "kamus_db"
    ).addTypeConverter(Converters(GsonParser(Gson())))
        .build()

}
