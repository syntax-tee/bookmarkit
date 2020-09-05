package com.taiye.bookmarkit.repository

import com.taiye.bookmarkit.database.dao.BookDao
import com.taiye.bookmarkit.database.dao.GenreDao
import com.taiye.bookmarkit.database.dao.ReadingListDao
import com.taiye.bookmarkit.database.dao.ReviewDao

class BookmarkitRepositoryImpl(
         private val bookDao: BookDao,
         private val  genreDao: GenreDao,
         private val readingListDao: ReadingListDao,
         private val reviewDao: ReviewDao
):BookmarkitRepository {
}