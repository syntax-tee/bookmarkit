
package com.taiye.bookmarkit.model

data class BookItem(
    val bookId: String,
    val name: String,
    var isSelected: Boolean = false
)