package com.example.kamussaya.feature_kamus.data.local

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.example.kamussaya.feature_kamus.data.util.JsonParser
import com.example.kamussaya.feature_kamus.domain.models.Meaning
import com.google.gson.reflect.TypeToken

@ProvidedTypeConverter
class Converters(private val jsonParser: JsonParser) {

    @TypeConverter
    fun fromJsonToMeaning(json: String): List<Meaning> {
        return jsonParser.fromJson<ArrayList<Meaning>>(
            json, object : TypeToken<ArrayList<Meaning>>() {}.type
        ) ?: emptyList()
    }

    @TypeConverter
    fun toJsonFromMeaning(meanings: List<Meaning>): String {
        return jsonParser.toJson(
            meanings, object : TypeToken<ArrayList<Meaning>>() {}.type
        ) ?: "[]"
    }

}
