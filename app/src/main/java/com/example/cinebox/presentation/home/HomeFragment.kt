package com.example.cinebox.presentation.home

import android.content.Intent
import android.content.res.ColorStateList
import android.graphics.PorterDuff
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.graphics.drawable.DrawableCompat
import androidx.core.widget.NestedScrollView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.cinebox.R
import com.example.cinebox.core.data.Resource
import com.example.cinebox.databinding.FragmentHomeBinding
import com.example.cinebox.presentation.detail.DetailActivity
import com.example.cinebox.presentation.home.adapter.MovieAdapter
import com.example.cinebox.presentation.search.SearchActivity
import com.example.cinebox.utils.*
import com.faltenreich.skeletonlayout.Skeleton
import com.faltenreich.skeletonlayout.applySkeleton
import com.google.android.material.R.attr.colorPrimary
import com.google.android.material.R.attr.colorSecondaryVariant
import dagger.hilt.android.AndroidEntryPoint
import java.lang.Integer.max
import java.lang.Integer.min

@AndroidEntryPoint
class HomeFragment : Fragment(), MovieAdapter.OnItemClickCallback, View.OnClickListener {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val homeViewModel: HomeViewModel by viewModels()
    private var alpha = 0
    private val nowPlayingAdapter = MovieAdapter(this)
    private val upcomingAdapter = MovieAdapter(this)
    private val topRatedAdapter = MovieAdapter(this)
    private lateinit var rvNowPlayingSkeleton: Skeleton
    private lateinit var rvUpcomingSkeleton: Skeleton
    private lateinit var rvTopRatedSkeleton: Skeleton

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentHomeBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

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
    }

    private fun setToolbar() {
        // Initial Set Toolbar
        binding.toolbar.root.background.alpha = 0
        binding.toolbar.etSearch.isFocusable = false
        setSearchBar(requireActivity().getColorStateListSecondaryVariant(),
            requireActivity().getColorFromAttr(colorSecondaryVariant))


        // On Scrolled
        binding.content.scrollView.setOnScrollChangeListener(NestedScrollView.OnScrollChangeListener { _, _, scrollY, _, _ ->
            val headerHeight: Int = binding.content.header.height - binding.toolbar.root.height
            val ratio = min(max(scrollY, 0), headerHeight).toFloat() / headerHeight

            alpha = (ratio * 255).toInt()

            // Set Toolbar
            binding.toolbar.root.background.alpha = alpha

            // Set Toolbar Items
            if (ratio >= 0.65F) {
                setSearchBar(requireActivity().getColorStateListPrimary(),
                    requireActivity().getColorFromAttr(colorPrimary))
            } else {
                setSearchBar(requireActivity().getColorStateListSecondaryVariant(),
                    requireActivity().getColorFromAttr(colorSecondaryVariant))
            }

        })


    }

    private fun setSearchBar(colorStroke: ColorStateList, colorIcon: Int) {
        binding.toolbar.tiSearch.apply {
            setBoxStrokeColorStateList(colorStroke)
            defaultHintTextColor = colorStroke
            hintTextColor = colorStroke
            editText!!.setTextColor(colorIcon)

            var searchIcon = requireActivity().getHelperDrawable(R.drawable.ic_baseline_search_24)
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
            addItemDecoration(HorizontalItemDecoration(margin.toPixel(requireContext())))
        }

        with(binding.content.rvUpcoming) {
            val margin = 16
            setHasFixedSize(true)
            adapter = upcomingAdapter
            addItemDecoration(HorizontalItemDecoration(margin.toPixel(requireContext())))
        }

        with(binding.content.rvToprated) {
            val margin = 16
            setHasFixedSize(true)
            adapter = topRatedAdapter
            addItemDecoration(HorizontalItemDecoration(margin.toPixel(requireContext())))
        }
    }

    private fun observeData() {

        // NOW PLAYING MOVIE
        homeViewModel.getAllNowPlayingMovie()
        homeViewModel.isNowPlayingLoading.observe(viewLifecycleOwner) { event ->
            event.getContentIfNotHandled()?.let {
                rvNowPlayingSkeleton.showSkeleton()
            }
        }
        homeViewModel.nowPlayingMovie.observe(viewLifecycleOwner) {
            nowPlayingAdapter.submitData(lifecycle, it)
            rvNowPlayingSkeleton.showOriginal()
        }

        // UPCOMING MOVIE
        homeViewModel.getAllUpcomingMovie()
        homeViewModel.isUpcomingLoading.observe(viewLifecycleOwner) { event ->
            event.getContentIfNotHandled()?.let {
                rvUpcomingSkeleton.showSkeleton()
            }

        }
        homeViewModel.upcomingMovie.observe(viewLifecycleOwner) {
            upcomingAdapter.submitData(lifecycle, it)
            rvUpcomingSkeleton.showOriginal()
        }

        // TOP RATED MOVIE
        homeViewModel.getAllTopRatedMovie()
        homeViewModel.isTopRatedLoading.observe(viewLifecycleOwner) { event ->
            event.getContentIfNotHandled()?.let {
                rvTopRatedSkeleton.showSkeleton()
            }
        }
        homeViewModel.topRatedMovie.observe(viewLifecycleOwner) {
            topRatedAdapter.submitData(lifecycle, it)
            rvTopRatedSkeleton.showOriginal()
        }

    }

    private fun setSkeleton() {
        val radius = 16

        with(binding.content) {
            rvNowPlayingSkeleton = rvNowPlaying.applySkeleton(R.layout.item_row_movie)
            rvUpcomingSkeleton = rvUpcoming.applySkeleton(R.layout.item_row_movie)
            rvTopRatedSkeleton = rvToprated.applySkeleton(R.layout.item_row_movie)
        }
        rvNowPlayingSkeleton.maskCornerRadius = radius.toPixel(requireContext()).toFloat()
        rvUpcomingSkeleton.maskCornerRadius = radius.toPixel(requireContext()).toFloat()
        rvTopRatedSkeleton.maskCornerRadius = radius.toPixel(requireContext()).toFloat()
    }

    override fun onResume() {
        super.onResume()
        binding.toolbar.root.background.alpha = alpha
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun onItemClicked(id: String) {
        val intent = Intent(requireContext(), DetailActivity::class.java)
        intent.putExtra(MOVIE_ID, id)
        startActivity(intent)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.et_search -> {
                val intent = Intent(requireContext(), SearchActivity::class.java)
                intent.putExtra(IS_SEARCH_BAR_PRESSED, true)
                startActivity(intent)
            }

            R.id.tv_see_all_now_playing -> {
                val intent = Intent(requireContext(), SearchActivity::class.java)
                intent.putExtra(SECTION, NOW_PLAYING)
                startActivity(intent)
            }

            R.id.tv_see_all_upcoming -> {
                val intent = Intent(requireContext(), SearchActivity::class.java)
                intent.putExtra(SECTION, UPCOMING)
                startActivity(intent)
            }

            R.id.tv_see_all_toprated -> {
                val intent = Intent(requireContext(), SearchActivity::class.java)
                intent.putExtra(SECTION, TOP_RATED)
                startActivity(intent)
            }
        }
    }

}