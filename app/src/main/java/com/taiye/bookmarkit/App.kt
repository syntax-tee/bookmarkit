package com.taiye.bookmarkit

import android.app.Application
import com.taiye.bookmarkit.database.BookMarkDatabase
import com.taiye.bookmarkit.model.Genre
import com.taiye.bookmarkit.repository.BookmarkitRepository
import com.taiye.bookmarkit.repository.BookmarkitRepositoryImpl


class App : Application() {

    companion object {
      private lateinit var instance: App

      private val database: BookMarkDatabase by lazy {
        BookMarkDatabase.buildDatabase(instance)
      }

      val repository: BookmarkitRepository by lazy {
        BookmarkitRepositoryImpl(
          database.bookDao(),
          database.genreDao(),
          database.readingListDao(),
          database.reviewDao()
        )
      }
    }

    override fun onCreate() {
      super.onCreate()
      instance = this

      if (repository.getGenres().isEmpty()) {
        repository.addGenres(
          listOf(
            Genre(name = "Action"),
            Genre(name = "Adventure"),
            Genre(name = "Classic"),
            Genre(name = "Mystery"),
            Genre(name = "Fantasy"),
            Genre(name = "Sci-Fi"),
            Genre(name = "History"),
            Genre(name = "Horror"),
            Genre(name = "Romance"),
            Genre(name = "Short Story"),
            Genre(name = "Biography"),
            Genre(name = "Poetry"),
            Genre(name = "Self-Help"),
            Genre(name = "Young novel"),
            Genre(name = "DevOps"),
            Genre(name = "Mobile"),
            Genre(name = "Frontend"),
            Genre(name = "Business Development"),
            Genre(name = "Database"),
            Genre(name = "Programming"),
            Genre(name = "UI/UX")
          )
        )
      }
    }
  }