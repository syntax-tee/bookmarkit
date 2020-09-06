package com.taiye.bookmarkit.database.dao

import androidx.room.*
import com.taiye.bookmarkit.model.Book


@Dao
interface BookDao{

    @Query("SELECT * FROM books")
    fun getBooks(): List<Book>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addBook(book: Book)

    @Delete
    fun removeBook(book: Book)

    @Query( "SELECT * FROM books WHERE id =:bookId")
    fun getBookById(bookId:String):Book


}