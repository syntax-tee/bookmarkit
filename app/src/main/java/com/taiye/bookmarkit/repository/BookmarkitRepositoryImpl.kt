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
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class BookmarkitRepositoryImpl(
    private val bookDao: BookDao,
    private val genreDao: GenreDao,
    private val readingListDao: ReadingListDao,
    private val reviewDao: ReviewDao
) : BookmarkitRepository {

    override suspend fun addBook(book: Book) = bookDao.addBook(book)

    override suspend fun getBooks() = bookDao.getBooks()

    override suspend fun getBookById(bookId: String): Book = bookDao.getBookById(bookId)

    override suspend fun removeBook(book: Book) = bookDao.removeBook(book)

    override fun getGenres() = genreDao.getGenres()

    override suspend fun getGenreById(genreId: String) = genreDao.getGenreById(genreId)

    override fun addGenres(genres: List<Genre>) = genreDao.addGenres(genres)

    override fun addReview(review: Review) = reviewDao.addReview(review)

    override fun removeReview(review: Review) = reviewDao.removeReview(review)

    override suspend fun getReviews(): List<BookReview> = reviewDao.getReviews()

    override fun getReviewsWithFlows(): Flow<List<BookReview>> = reviewDao.getReviewsWithFlows()

    override fun getReviewById(reviewId: String): BookReview = reviewDao.getReviewById(reviewId)

    override fun updateReview(review: Review) = reviewDao.updateReview(review)

    override suspend fun addReadingList(readingList: ReadingList) =
        readingListDao.addReadingList(readingList)

    override suspend fun getReadingList(): List<ReadingListsWithBooks> =
        readingListDao.getReadingList().map {
            ReadingListsWithBooks(it.id, it.name, emptyList())
        }

    override fun getReadingListsFlow(): Flow<List<ReadingListsWithBooks>> {
      return  readingListDao.getReadingListFlow().map { items ->
            items.map {
                ReadingListsWithBooks(it.id, it.name, emptyList())
            }
        }
    }

    override suspend fun removeReadingList(readingList: ReadingList) =
        readingListDao.removeReadingList(readingList)

    override suspend fun getBooksByGenre(genreId: String): List<BookAndGenre> {
        genreDao.getBooksByGenre(genreId).let { booksByGenre ->
            val books = booksByGenre.books ?: return emptyList()

            return books.map { BookAndGenre(it, booksByGenre.genre) }
        }
    }

    override suspend fun getBooksByRating(rating: Int): List<BookAndGenre> {
        val reviewsByRating = reviewDao.getReviewsByRating(rating)
        return reviewsByRating.map { BookAndGenre(it.book, genreDao.getGenreById(it.book.genreId)) }
    }


}