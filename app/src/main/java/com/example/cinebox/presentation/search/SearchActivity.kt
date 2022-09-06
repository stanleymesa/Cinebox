package com.example.cinebox.presentation.search

import android.content.Context
import android.content.Intent
import android.content.res.ColorStateList
import android.graphics.PorterDuff
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.graphics.drawable.DrawableCompat
import androidx.core.view.isVisible
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.viewModelScope
import androidx.recyclerview.widget.RecyclerView
import com.example.cinebox.R
import com.example.cinebox.core.domain.model.Movie
import com.example.cinebox.databinding.ActivitySearchBinding
import com.example.cinebox.presentation.detail.DetailActivity
import com.example.cinebox.presentation.home.HomeViewModel
import com.example.cinebox.presentation.search.adapter.SearchAdapter
import com.example.cinebox.utils.*
import com.google.android.material.R.attr.colorPrimary
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SearchActivity : AppCompatActivity(), SearchAdapter.OnItemClickCallback,
    View.OnClickListener {

    private var _binding: ActivitySearchBinding? = null
    private val binding get() = _binding!!
    private val searchViewModel: SearchViewModel by viewModels()
    private val homeViewModel: HomeViewModel by viewModels()
    private val isHomeSearchBarPressed get() = intent.getBooleanExtra(IS_SEARCH_BAR_PRESSED, false)
    private val section get() = intent.getStringExtra(SECTION)
    private val searchAdapter = SearchAdapter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivitySearchBinding.inflate(layoutInflater)
        setContentView(binding.root)

        init()
    }

    private fun init() {
        setToolbar()
        setAdapter()
        observeData()
        scrollToTopListAdapter()
        binding.toolbar.ivBack.setOnClickListener(this)
    }

    @Suppress("DEPRECATION")
    private fun setToolbar() {
        setSearchBar(this.getColorStateListPrimary(), this.getColorFromAttr(colorPrimary))

        if (isHomeSearchBarPressed) {
            binding.toolbar.etSearch.requestFocus()
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.toggleSoftInput(InputMethodManager.SHOW_FORCED,
                InputMethodManager.HIDE_IMPLICIT_ONLY)
        }
    }

    private fun setSearchBar(colorStroke: ColorStateList, colorIcon: Int) {
        binding.toolbar.tiSearch.apply {
            setBoxStrokeColorStateList(colorStroke)
            defaultHintTextColor = colorStroke
            hintTextColor = colorStroke
            editText!!.setTextColor(colorIcon)

            var searchIcon =
                this@SearchActivity.getHelperDrawable(R.drawable.ic_baseline_search_24)
            searchIcon = DrawableCompat.wrap(searchIcon)
            DrawableCompat.setTint(searchIcon, colorIcon)
            DrawableCompat.setTintMode(searchIcon, PorterDuff.Mode.SRC_IN)
            editText!!.setCompoundDrawablesWithIntrinsicBounds(searchIcon, null, null, null)
        }

        binding.toolbar.root.setOnClickListener {
            binding.toolbar.etSearch.clearFocus()
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(binding.toolbar.etSearch.windowToken, 0)
        }

        binding.root.setOnClickListener {
            binding.toolbar.etSearch.clearFocus()
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(binding.toolbar.etSearch.windowToken, 0)
        }
    }

    private fun setAdapter() {
        with(binding.rvSearch) {
            val margin = 24
            setHasFixedSize(true)
            adapter = searchAdapter
            addItemDecoration(VerticalItemDecoration(margin.toPixel(this@SearchActivity)))
        }
    }

    private fun scrollToTopListAdapter() {
        searchAdapter.registerAdapterDataObserver(object :
            RecyclerView.AdapterDataObserver() {
            override fun onChanged() {}

            override fun onItemRangeChanged(positionStart: Int, itemCount: Int) {}

            override fun onItemRangeChanged(positionStart: Int, itemCount: Int, payload: Any?) {}

            override fun onItemRangeInserted(positionStart: Int, itemCount: Int) {}

            override fun onItemRangeRemoved(positionStart: Int, itemCount: Int) {
                binding.rvSearch.scrollToPosition(0)
            }

            override fun onItemRangeMoved(fromPosition: Int, toPosition: Int, itemCount: Int) {}
        })
    }

    private fun observeData() {

        var initialSearch = ""
        binding.toolbar.etSearch.addTextChangedListener {
            val searchText = it.toString().trim()

            // if trim
            if (searchText == initialSearch) {
                return@addTextChangedListener
            }

            initialSearch = searchText

            searchViewModel.viewModelScope.launch {
                delay(300L)
                if (searchText != initialSearch || searchText.isEmpty()) {
                    return@launch
                }

                searchViewModel.getSearchMovie(it.toString()).observe(this@SearchActivity) { data ->
                    searchAdapter.submitData(lifecycle, data)
                }
            }
        }


        when (section) {
            NOW_PLAYING -> {
                homeViewModel.getAllNowPlayingMovie()
                homeViewModel.isNowPlayingLoading.observe(this) { event ->
                    event.getContentIfNotHandled()?.let {
                        binding.progressBar.isVisible = true
                    }
                }
                homeViewModel.nowPlayingMovie.observe(this) {
                    searchAdapter.submitData(lifecycle, it)
                    binding.progressBar.isVisible = false
                }
            }

            UPCOMING -> {
                homeViewModel.getAllUpcomingMovie()
                homeViewModel.isUpcomingLoading.observe(this) { event ->
                    event.getContentIfNotHandled()?.let {
                        binding.progressBar.isVisible = true
                    }

                }
                homeViewModel.upcomingMovie.observe(this) {
                    searchAdapter.submitData(lifecycle, it)
                    binding.progressBar.isVisible = false
                }
            }

            TOP_RATED -> {
                homeViewModel.getAllTopRatedMovie()
                homeViewModel.isTopRatedLoading.observe(this) { event ->
                    event.getContentIfNotHandled()?.let {
                        binding.progressBar.isVisible = true
                    }
                }
                homeViewModel.topRatedMovie.observe(this) {
                    searchAdapter.submitData(lifecycle, it)
                    binding.progressBar.isVisible = false
                }
            }
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun onItemClicked(movie: Movie) {
        val intent = Intent(this, DetailActivity::class.java)
        intent.putExtra(MOVIE_ID, movie.id)
        startActivity(intent)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.iv_back -> finish()
        }
    }
}