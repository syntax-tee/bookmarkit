package com.taiye.bookmarkit.database.converters

import androidx.room.TypeConverter
import java.util.*


class DateConverter {

    @TypeConverter
    fun fromTimeStamp(value:Long?): Date?{
        return  Date(value ?: 0)
    }

    @TypeConverter
    fun dateToTimestamp(date: Date?): Long = date?.time ?: 0
}