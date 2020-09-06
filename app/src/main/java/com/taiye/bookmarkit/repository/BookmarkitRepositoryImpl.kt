package com.taiye.bookmarkit.repository

import com.taiye.bookmarkit.database.dao.BookDao
import com.taiye.bookmarkit.database.dao.GenreDao
import com.taiye.bookmarkit.database.dao.ReadingListDao
import com.taiye.bookmarkit.database.dao.ReviewDao
import com.taiye.bookmarkit.model.Book
import com.taiye.bookmarkit.model.Genre
import com.taiye.bookmarkit.model.ReadingList
import com.taiye.bookmarkit.model.Review
import com.taiye.bookmarkit.model.relations.BookAndGenre
import com.taiye.bookmarkit.model.relations.BookReview
import com.taiye.bookmarkit.model.relations.ReadingListsWithBooks

class BookmarkitRepositoryImpl(
         private val bookDao: BookDao,
         private val  genreDao: GenreDao,
         private val readingListDao: ReadingListDao,
         private val reviewDao: ReviewDao
):BookmarkitRepository {

    override fun addBook(book: Book) = bookDao.addBook(book)

    override fun getBooks() = bookDao.getBooks()

    override fun getBookById(bookId: String): Book  = bookDao.getBookById(bookId)

    override fun removeBook(book: Book) = bookDao.removeBook(book)

    override fun getGenres() = genreDao.getGenres()

    override fun getGenreById(genreId: String) = genreDao.getGenreById(genreId)

    override fun addGenres(genres: List<Genre>)  = genreDao.addGenres(genres)

    override fun addReview(review: Review) = reviewDao.addReview(review)

    override fun removeReview(review: Review) = reviewDao.removeReview(review)

    override fun getReviews(): List<BookReview>  = reviewDao.getReviews().map { BookReview(it,bookDao.getBookById(it.bookId)) }

    override fun getReviewById(reviewId: String): BookReview  {
        val review = reviewDao.getReviewById(reviewId)
        return BookReview(review, bookDao.getBookById(review.bookId));
    }
    override fun updateReview(review: Review) = reviewDao.updateReview(review)

    override fun addReadingList(readingList: ReadingList) = readingListDao.addReadingList(readingList)

    override fun getReadingList(): List<ReadingListsWithBooks> = readingListDao.getReadingList().map {
        ReadingListsWithBooks(it.id, it.name, emptyList())
    }

    override fun removeReadingList(readingList: ReadingList) = readingListDao.removeReadingList(readingList)

    override fun getBooksByGenre(genreId: String): List<BookAndGenre> {
        genreDao.getBooksByGenre(genreId).let { booksByGenre ->
            val books = booksByGenre.books ?: return emptyList()

            return  books.map { BookAndGenre(it,booksByGenre.genre) }
        }
    }

}