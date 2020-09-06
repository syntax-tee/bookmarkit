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


    fun addBook(book: Book)

    fun getBooks(): List<BookAndGenre>

    fun getBookById(bookId: String):Book

    fun removeBook(book:Book)

    fun getGenres(): List<Genre>

    fun getGenreById(genreId:String):Genre

    fun addGenres(genres:List<Genre>)

    fun addReview(review: Review)

    fun removeReview(review: Review)

    fun getReviews():List<BookReview>

    fun getReviewById(reviewId:String):BookReview

    fun updateReview(review: Review)

    fun addReadingList(readingList: ReadingList)

    fun getReadingList():List<ReadingListsWithBooks>

    fun removeReadingList(readingList: ReadingList)

    fun getBooksByGenre(genreId: String): List<BookAndGenre>

    fun getBooksByRating(rating:Int): List<BookAndGenre>

}