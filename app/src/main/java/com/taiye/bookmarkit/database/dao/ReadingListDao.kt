package com.taiye.bookmarkit.database.dao

import androidx.room.*
import com.taiye.bookmarkit.model.ReadingList
import kotlinx.coroutines.flow.Flow


@Dao
interface ReadingListDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend  fun addReadingList(readingList: ReadingList)

    @Query("SELECT * FROM readingList")
    suspend  fun getReadingList(): List<ReadingList>

    @Query("SELECT * FROM readinglist")
    fun getReadingListFlow(): Flow<List<ReadingList>>

    @Delete
    suspend  fun removeReadingList(readingList: ReadingList)
}


