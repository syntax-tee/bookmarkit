package com.taiye.bookmarkit.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.taiye.bookmarkit.database.dao.BookDao
import com.taiye.bookmarkit.database.dao.GenreDao
import com.taiye.bookmarkit.database.dao.ReadingListDao
import com.taiye.bookmarkit.database.dao.ReviewDao
import com.taiye.bookmarkit.model.Book
import com.taiye.bookmarkit.model.Genre
import com.taiye.bookmarkit.model.ReadingList
import com.taiye.bookmarkit.model.Review


const val DATABASE_VERSION = 1

@Database(
     entities = [Book::class, Genre::class, ReadingList::class, Review::class],
     version = DATABASE_VERSION
)
abstract class BookMarkDatabase : RoomDatabase(){


    companion object{
      private const val  DATABASE_NAME = "bookmark"

        fun buildDatabase(context: Context): BookMarkDatabase {
            return Room.databaseBuilder(
                context,
                BookMarkDatabase::class.java,
                DATABASE_NAME
            )
                .allowMainThreadQueries()
                .build()
        }
    }

    abstract fun bookDao(): BookDao

    abstract fun genreDao(): GenreDao

    abstract fun readingListDao(): ReadingListDao

    abstract fun reviewDao(): ReviewDao
}