/*
 * Copyright (c) 2020 Razeware LLC
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * Notwithstanding the foregoing, you may not use, copy, modify, merge, publish,
 * distribute, sublicense, create a derivative work, and/or sell copies of the
 * Software in any work that is designed, intended, or marketed for pedagogical or
 * instructional purposes related to programming, coding, application development,
 * or information technology.  Permission for such use, copying, modification,
 * merger, publication, distribution, sublicensing, creation of derivative works,
 * or sale is expressly withheld.
 *
 * This project and source code may use libraries or frameworks that are
 * released under various Open-Source licenses. Use of those libraries and
 * frameworks are governed by their own individual licenses.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package com.taiye.bookmarkit.ui.reviews

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.taiye.bookmarkit.App
import com.taiye.bookmarkit.model.relations.BookReview
import com.taiye.bookmarkit.ui.addReview.AddBookReviewActivity
import com.taiye.bookmarkit.ui.bookReviewDetails.BookReviewDetailsActivity
import com.taiye.bookmarkit.utils.createAndShowDialog
import com.taiye.bookmarkit.R
import kotlinx.android.synthetic.main.fragment_reviews.*
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

/**
 * Fetches and displays notes from the API.
 */
private const val REQUEST_CODE_ADD_REVIEW = 102

class BookReviewsFragment : Fragment() {

    private val adapter by lazy { BookReviewAdapter(::onItemSelected, ::onItemLongTapped) }

    private val repository by lazy { App.repository }
    private val bookReviewFlow by lazy { repository.getReviewsWithFlows() }


    override fun onCreateView(
      inflater: LayoutInflater, container: ViewGroup?,
      savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_reviews, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initListeners()
        initUi()
        lifecycleScope.launch {
            loadBookReviews()
        }
    }

    private fun initUi() {
        reviewsRecyclerView.layoutManager = LinearLayoutManager(context)
        reviewsRecyclerView.adapter = adapter
        pullToRefresh.isEnabled = false
    }

    private fun initListeners() {
        pullToRefresh.isEnabled = false

        addBookReview.setOnClickListener {
            startActivityForResult(
              AddBookReviewActivity.getIntent(requireContext()), REQUEST_CODE_ADD_REVIEW
            )
        }

        pullToRefresh.setOnRefreshListener {
            lifecycleScope.launch {
                loadBookReviews()
            }
        }
    }

    private fun onItemSelected(item: BookReview) {
        startActivity(BookReviewDetailsActivity.getIntent(requireContext(), item))
    }

    private fun onItemLongTapped(item: BookReview) {
        createAndShowDialog(requireContext(),
          getString(R.string.delete_title),
          getString(R.string.delete_review_message, item.book.name),
          onPositiveAction = { removeReviewFromRepo(item) })
    }

    private fun removeReviewFromRepo(item: BookReview) = lifecycleScope.launch {
        repository.removeReview(item.review)
        lifecycleScope.launch {
            loadBookReviews()
        }
    }

    private suspend fun loadBookReviews() = lifecycleScope.launch {
        pullToRefresh.isRefreshing = false

        bookReviewFlow.catch { error ->
            error.printStackTrace()
        }.collect { bookReviewFlow->
            adapter.setData(bookReviewFlow)
        }

    }
}