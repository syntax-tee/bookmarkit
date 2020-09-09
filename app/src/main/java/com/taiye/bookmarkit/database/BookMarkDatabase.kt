package com.taiye.bookmarkit.database

import android.content.Context
import androidx.room.*
import com.taiye.bookmarkit.database.converters.BooksIdsConverter
import com.taiye.bookmarkit.database.converters.DateConverter
import com.taiye.bookmarkit.database.converters.ReadEntryConverter
import com.taiye.bookmarkit.database.dao.BookDao
import com.taiye.bookmarkit.database.dao.GenreDao
import com.taiye.bookmarkit.database.dao.ReadingListDao
import com.taiye.bookmarkit.database.dao.ReviewDao
import com.taiye.bookmarkit.database.migration.migration_1_2
import com.taiye.bookmarkit.database.migration.migration_2_3
import com.taiye.bookmarkit.database.migration.migration_3_4
import com.taiye.bookmarkit.model.Book
import com.taiye.bookmarkit.model.Genre
import com.taiye.bookmarkit.model.ReadingList
import com.taiye.bookmarkit.model.Review


const val DATABASE_VERSION = 4

@Database(
     entities = [Book::class, Genre::class, ReadingList::class, Review::class],
     version = DATABASE_VERSION,
    exportSchema = false
)
@TypeConverters(DateConverter::class,ReadEntryConverter::class,BooksIdsConverter::class)
abstract class BookMarkDatabase : RoomDatabase(){


    companion object{
      private const val  DATABASE_NAME = "bookmark"

        fun buildDatabase(context: Context): BookMarkDatabase {
            return Room.databaseBuilder(context, BookMarkDatabase::class.java, DATABASE_NAME).addMigrations(migration_1_2, migration_2_3, migration_3_4).allowMainThreadQueries().build()
        }
    }

    abstract fun bookDao(): BookDao

    abstract fun genreDao(): GenreDao

    abstract fun readingListDao(): ReadingListDao

    abstract fun reviewDao(): ReviewDao
}