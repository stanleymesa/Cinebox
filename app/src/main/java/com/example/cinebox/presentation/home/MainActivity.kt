package com.example.cinebox.presentation.home

import android.content.Intent
import android.content.res.ColorStateList
import android.graphics.PorterDuff
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.graphics.drawable.DrawableCompat
import androidx.core.widget.NestedScrollView
import com.example.cinebox.R
import com.example.cinebox.databinding.ActivityMainBinding
import com.example.cinebox.presentation.detail.DetailActivity
import com.example.cinebox.presentation.search.SearchActivity
import com.example.core.ui.home.MovieAdapter
import com.example.core.utils.*
import com.faltenreich.skeletonlayout.Skeleton
import com.faltenreich.skeletonlayout.applySkeleton
import com.google.android.material.R.attr.colorPrimary
import com.google.android.material.R.attr.colorSecondaryVariant
import com.google.android.play.core.splitinstall.SplitInstallManagerFactory
import com.google.android.play.core.splitinstall.SplitInstallRequest
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), MovieAdapter.OnItemClickCallback, View.OnClickListener {

    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!
    private val homeViewModel: HomeViewModel by viewModels()
    private var alpha = 0
    private val nowPlayingAdapter = MovieAdapter(this)
    private val upcomingAdapter = MovieAdapter(this)
    private val topRatedAdapter = MovieAdapter(this)
    private lateinit var rvNowPlayingSkeleton: Skeleton
    private lateinit var rvUpcomingSkeleton: Skeleton
    private lateinit var rvTopRatedSkeleton: Skeleton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        init()
    }

    private fun init() {
        setToolbar()
        setAdapter()
        setSkeleton()
        observeData()
        binding.toolbar.etSearch.setOnClickListener(this)
        binding.content.tvSeeAllNowPlaying.setOnClickListener(this)
        binding.content.tvSeeAllUpcoming.setOnClickListener(this)
        binding.content.tvSeeAllToprated.setOnClickListener(this)
        binding.fab.setOnClickListener(this)
    }

    private fun setToolbar() {
        // Initial Set Toolbar
        binding.toolbar.root.background.alpha = 0
        binding.toolbar.etSearch.isFocusable = false
        setSearchBar(this.getColorStateListSecondaryVariant(),
            this.getColorFromAttr(colorSecondaryVariant))


        // On Scrolled
        binding.content.scrollView.setOnScrollChangeListener(NestedScrollView.OnScrollChangeListener { _, _, scrollY, _, _ ->
            val headerHeight: Int = binding.content.header.height - binding.toolbar.root.height
            val ratio = Integer.min(Integer.max(scrollY, 0), headerHeight).toFloat() / headerHeight

            alpha = (ratio * 255).toInt()

            // Set Toolbar
            binding.toolbar.root.background.alpha = alpha

            // Set Toolbar Items
            if (ratio >= 0.65F) {
                setSearchBar(this.getColorStateListPrimary(),
                    this.getColorFromAttr(colorPrimary))
            } else {
                setSearchBar(this.getColorStateListSecondaryVariant(),
                    this.getColorFromAttr(colorSecondaryVariant))
            }

        })


    }

    private fun setSearchBar(colorStroke: ColorStateList, colorIcon: Int) {
        binding.toolbar.tiSearch.apply {
            setBoxStrokeColorStateList(colorStroke)
            defaultHintTextColor = colorStroke
            hintTextColor = colorStroke
            editText!!.setTextColor(colorIcon)

            var searchIcon = this@MainActivity.getHelperDrawable(R.drawable.ic_baseline_search_24)
            searchIcon = DrawableCompat.wrap(searchIcon)
            DrawableCompat.setTint(searchIcon, colorIcon)
            DrawableCompat.setTintMode(searchIcon, PorterDuff.Mode.SRC_IN)
            editText!!.setCompoundDrawablesWithIntrinsicBounds(searchIcon, null, null, null)
        }
    }

    private fun setAdapter() {
        with(binding.content.rvNowPlaying) {
            val margin = 16
            setHasFixedSize(true)
            adapter = nowPlayingAdapter
            addItemDecoration(HorizontalItemDecoration(margin.toPixel(applicationContext)))
        }

        with(binding.content.rvUpcoming) {
            val margin = 16
            setHasFixedSize(true)
            adapter = upcomingAdapter
            addItemDecoration(HorizontalItemDecoration(margin.toPixel(applicationContext)))
        }

        with(binding.content.rvToprated) {
            val margin = 16
            setHasFixedSize(true)
            adapter = topRatedAdapter
            addItemDecoration(HorizontalItemDecoration(margin.toPixel(applicationContext)))
        }
    }

    private fun observeData() {

        // NOW PLAYING MOVIE
        homeViewModel.getAllNowPlayingMovie()
        homeViewModel.isNowPlayingLoading.observe(this) { event ->
            event.getContentIfNotHandled()?.let {
                rvNowPlayingSkeleton.showSkeleton()
            }
        }
        homeViewModel.nowPlayingMovie.observe(this) {
            nowPlayingAdapter.submitData(lifecycle, it)
            rvNowPlayingSkeleton.showOriginal()
        }

        // UPCOMING MOVIE
        homeViewModel.getAllUpcomingMovie()
        homeViewModel.isUpcomingLoading.observe(this) { event ->
            event.getContentIfNotHandled()?.let {
                rvUpcomingSkeleton.showSkeleton()
            }

        }
        homeViewModel.upcomingMovie.observe(this) {
            upcomingAdapter.submitData(lifecycle, it)
            rvUpcomingSkeleton.showOriginal()
        }

        // TOP RATED MOVIE
        homeViewModel.getAllTopRatedMovie()
        homeViewModel.isTopRatedLoading.observe(this) { event ->
            event.getContentIfNotHandled()?.let {
                rvTopRatedSkeleton.showSkeleton()
            }
        }
        homeViewModel.topRatedMovie.observe(this) {
            topRatedAdapter.submitData(lifecycle, it)
            rvTopRatedSkeleton.showOriginal()
        }

    }

    private fun setSkeleton() {
        val radius = 16

        with(binding.content) {
            rvNowPlayingSkeleton =
                rvNowPlaying.applySkeleton(com.example.core.R.layout.item_row_movie)
            rvUpcomingSkeleton = rvUpcoming.applySkeleton(com.example.core.R.layout.item_row_movie)
            rvTopRatedSkeleton = rvToprated.applySkeleton(com.example.core.R.layout.item_row_movie)
        }
        rvNowPlayingSkeleton.maskCornerRadius = radius.toPixel(applicationContext).toFloat()
        rvUpcomingSkeleton.maskCornerRadius = radius.toPixel(applicationContext).toFloat()
        rvTopRatedSkeleton.maskCornerRadius = radius.toPixel(applicationContext).toFloat()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun onItemClicked(id: String) {
        val intent = Intent(applicationContext, DetailActivity::class.java)
        intent.putExtra(MOVIE_ID, id)
        startActivity(intent)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.et_search -> {
                val intent = Intent(applicationContext, SearchActivity::class.java)
                intent.putExtra(IS_SEARCH_BAR_PRESSED, true)
                startActivity(intent)
            }

            R.id.tv_see_all_now_playing -> {
                val intent = Intent(applicationContext, SearchActivity::class.java)
                intent.putExtra(SECTION, NOW_PLAYING)
                startActivity(intent)
            }

            R.id.tv_see_all_upcoming -> {
                val intent = Intent(applicationContext, SearchActivity::class.java)
                intent.putExtra(SECTION, UPCOMING)
                startActivity(intent)
            }

            R.id.tv_see_all_toprated -> {
                val intent = Intent(applicationContext, SearchActivity::class.java)
                intent.putExtra(SECTION, TOP_RATED)
                startActivity(intent)
            }

            R.id.fab -> {
                try {
                    installFavouriteModule()
                } catch (ex: Exception) {
                    Toast.makeText(this, "Module not found", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun installFavouriteModule() {
        val splitInstallManager = SplitInstallManagerFactory.create(this)
        val moduleChat = "favourite"
        if (splitInstallManager.installedModules.contains(moduleChat)) {
            moveToFavourite()
            Toast.makeText(this, "Open module", Toast.LENGTH_SHORT).show()
        } else {
            val request = SplitInstallRequest.newBuilder()
                .addModule(moduleChat)
                .build()
            splitInstallManager.startInstall(request)
                .addOnSuccessListener {
                    Toast.makeText(this, "Success installing module", Toast.LENGTH_SHORT).show()
                    moveToFavourite()
                }
                .addOnFailureListener {
                    Log.e("ERROR", it.message.toString())
                    Toast.makeText(this, it.message.toString(), Toast.LENGTH_SHORT).show()
                }
        }
    }

    private fun moveToFavourite() {
        startActivity(Intent(applicationContext, Class.forName("com.example.favourite.FavouriteActivity")))
    }

}