package com.taiye.bookmarkit.database.dao

import androidx.room.*
import com.taiye.bookmarkit.model.ReadingList


@Dao
interface ReadingListDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend  fun addReadingList(readingList: ReadingList)

    @Query("SELECT * FROM ReadingList")
    suspend  fun getReadingList(): List<ReadingList>

    @Delete
    suspend  fun removeReadingList(readingList: ReadingList)
}


