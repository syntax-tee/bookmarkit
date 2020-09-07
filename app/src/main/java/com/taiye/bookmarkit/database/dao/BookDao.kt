package com.taiye.bookmarkit.database.dao

import androidx.room.*
import com.taiye.bookmarkit.model.Book
import com.taiye.bookmarkit.model.relations.BookAndGenre
import com.taiye.bookmarkit.model.relations.BookReview
import com.taiye.bookmarkit.model.relations.BooksByGenre


@Dao
interface BookDao{

    @Query("SELECT * FROM books")
    suspend  fun getBooks(): List<BookAndGenre>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addBook(book: Book)

    @Delete
    suspend fun removeBook(book: Book)

    @Query( "SELECT * FROM books WHERE id =:bookId")
    suspend fun getBookById(bookId:String):Book


}