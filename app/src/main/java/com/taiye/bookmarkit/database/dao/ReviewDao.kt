package com.taiye.bookmarkit.database.dao

import androidx.room.*
import com.taiye.bookmarkit.model.Review
import com.taiye.bookmarkit.model.relations.BookReview


@Dao
interface ReviewDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addReview(review: Review)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun updateReview(review: Review)

    @Query("SELECT * FROM review")
    fun getReviews(): List<BookReview>

    @Query("SELECT * FROM review WHERE id =:reviewId")
    fun getReviewById(reviewId: String): BookReview

    @Delete
    fun removeReview(review: Review)

    @Transaction
    @Query("SELECT * FROM review where rating >= :rating")
    fun getReviewsByRating(rating: Int): List<BookReview>

}