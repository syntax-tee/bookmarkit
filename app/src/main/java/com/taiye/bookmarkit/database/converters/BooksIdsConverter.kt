package com.taiye.bookmarkit.database.converters

import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.google.gson.reflect.TypeToken
import com.taiye.bookmarkit.App

class BooksIdsConverter {

    @TypeConverter
    fun fromBookIds(list:List<String>):String = App.gson.toJson(list)

    @TypeConverter
    fun toBookIds(json:String):List<String>{
        val typeToken = object: TypeToken<List<String>>() {}.type

        return  try{
            App.gson.fromJson(json, typeToken)
        }catch (error:Throwable){
            emptyList()
        }
    }

}
