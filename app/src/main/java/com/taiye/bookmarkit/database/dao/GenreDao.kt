package com.taiye.bookmarkit.database.dao

import androidx.room.*
import com.taiye.bookmarkit.model.Genre
import com.taiye.bookmarkit.model.relations.BooksByGenre


@Dao
interface GenreDao{


    @Query("SELECT * FROM genre")
    fun getGenres(): List<Genre>

    @Query("SELECT * FROM genre WHERE id= :genreId")
    fun getGenreById(genreId:String):Genre

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addGenres(genres:List<Genre>)

    @Transaction
    @Query("SELECT * FROM genre where id = :genreId")
    fun getBooksByGenre(genreId: String): BooksByGenre

    @Transaction
    @Query("SELECT * FROM genre")
    fun getBooksByGenres(): BooksByGenre



}