package com.taiye.bookmarkit.database.dao

import androidx.room.*
import com.taiye.bookmarkit.model.ReadingList


@Dao
interface ReadingListDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addReadingList(readingList: ReadingList)


    @Query("SELECT * FROM ReadingList")
    fun getReadingList(): List<ReadingList>

    @Delete
    fun removeReadingList(readingList: ReadingList)
}


