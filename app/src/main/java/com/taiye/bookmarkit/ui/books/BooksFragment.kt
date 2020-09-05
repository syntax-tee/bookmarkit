
package com.taiye.bookmarkit.ui.books

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.taiye.bookmarkit.model.Book
import com.taiye.bookmarkit.model.relations.BookAndGenre
import com.taiye.bookmarkit.ui.addBook.AddBookActivity
import com.taiye.bookmarkit.ui.filter.Filter
import com.taiye.bookmarkit.ui.filter.FilterPickerDialogFragment
import com.taiye.bookmarkit.utils.createAndShowDialog
import com.taiye.bookmarkit.R
import kotlinx.android.synthetic.main.fragment_books.*
import kotlinx.android.synthetic.main.fragment_reviews.pullToRefresh

private const val REQUEST_CODE_ADD_BOOK = 101

class BooksFragment : Fragment() {

  private val adapter by lazy { BookAdapter(::onItemLongTapped) }
  private var filter: Filter? = null

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
      savedInstanceState: Bundle?): View? {
    return inflater.inflate(R.layout.fragment_books, container, false)
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    initUi()
  }

  override fun onStart() {
    super.onStart()
    loadBooks()
  }

  private fun initUi() {
    pullToRefresh.setOnRefreshListener {
      loadBooks()
    }

    booksRecyclerView.adapter = adapter
    booksRecyclerView.layoutManager = LinearLayoutManager(requireContext())
    addBook.setOnClickListener {
      startActivityForResult(AddBookActivity.getIntent(requireContext()), REQUEST_CODE_ADD_BOOK)
    }

    filterBooks.setOnClickListener {
      val dialog = FilterPickerDialogFragment { filter ->
        this.filter = filter

        loadBooks()
      }

      dialog.show(requireFragmentManager(), null)
    }
  }

  private fun loadBooks() {
    pullToRefresh.isRefreshing = true

    val books = emptyList<BookAndGenre>() // TODO fetch from DB

    adapter.setData(books)
    pullToRefresh.isRefreshing = false
  }

  private fun onItemLongTapped(book: Book) {
    createAndShowDialog(requireContext(),
        getString(R.string.delete_title),
        getString(R.string.delete_message, book.name),
        onPositiveAction = { removeBook(book) }
    )
  }

  private fun removeBook(book: Book) {
    // TODO remove book
    loadBooks()
  }
}