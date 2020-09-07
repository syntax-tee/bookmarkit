package com.taiye.bookmarkit.repository

import com.taiye.bookmarkit.database.dao.BookDao
import com.taiye.bookmarkit.model.Book
import com.taiye.bookmarkit.model.Genre
import com.taiye.bookmarkit.model.ReadingList
import com.taiye.bookmarkit.model.Review
import com.taiye.bookmarkit.model.relations.BookAndGenre
import com.taiye.bookmarkit.model.relations.BookReview
import com.taiye.bookmarkit.model.relations.ReadingListsWithBooks

interface BookmarkitRepository{


    suspend fun addBook(book: Book)

    suspend fun getBooks(): List<BookAndGenre>

    suspend fun getBookById(bookId: String):Book

    suspend fun removeBook(book:Book)

     fun getGenres(): List<Genre>

    suspend fun getGenreById(genreId:String):Genre

     fun addGenres(genres:List<Genre>)

    fun addReview(review: Review)

    fun removeReview(review: Review)

    fun getReviews():List<BookReview>

    fun getReviewById(reviewId:String):BookReview

    fun updateReview(review: Review)

    suspend fun addReadingList(readingList: ReadingList)

    suspend fun getReadingList():List<ReadingListsWithBooks>

    suspend fun removeReadingList(readingList: ReadingList)

    suspend fun getBooksByGenre(genreId: String): List<BookAndGenre>

    suspend fun getBooksByRating(rating:Int): List<BookAndGenre>

}