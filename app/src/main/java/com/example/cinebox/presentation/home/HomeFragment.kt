package com.example.cinebox.presentation.home

import android.content.res.ColorStateList
import android.graphics.PorterDuff
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.graphics.drawable.DrawableCompat
import androidx.core.widget.NestedScrollView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.cinebox.R
import com.example.cinebox.databinding.FragmentHomeBinding
import com.example.cinebox.presentation.home.adapter.MovieAdapter
import com.example.cinebox.utils.*
import com.google.android.material.R.attr.colorPrimary
import com.google.android.material.R.attr.colorSecondaryVariant
import dagger.hilt.android.AndroidEntryPoint
import java.lang.Integer.max
import java.lang.Integer.min

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private var alpha = 0
    private val nowPlayingAdapter = MovieAdapter()
    private val upcomingAdapter = MovieAdapter()
    private val topRatedAdapter = MovieAdapter()
    private val homeViewModel: HomeViewModel by viewModels()

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
        observeData()
    }

    private fun setAdapter() {
        with(binding.content.rvNowPlaying) {
            val margin = 16
            setHasFixedSize(true)
            adapter = nowPlayingAdapter
            addItemDecoration(HomeItemDecoration(margin.toPixel(requireContext())))
        }

        with(binding.content.rvUpcoming) {
            val margin = 16
            setHasFixedSize(true)
            adapter = upcomingAdapter
            addItemDecoration(HomeItemDecoration(margin.toPixel(requireContext())))
        }

        with(binding.content.rvToprated) {
            val margin = 16
            setHasFixedSize(true)
            adapter = topRatedAdapter
            addItemDecoration(HomeItemDecoration(margin.toPixel(requireContext())))
        }
    }

    private fun observeData() {
        homeViewModel.getAllNowPlayingMovie().observe(viewLifecycleOwner) {
            nowPlayingAdapter.submitData(lifecycle, it)
        }

        homeViewModel.getAllUpcomingMovie().observe(viewLifecycleOwner) {
            upcomingAdapter.submitData(lifecycle, it)
        }

        homeViewModel.getAllTopRatedMovie().observe(viewLifecycleOwner) {
            topRatedAdapter.submitData(lifecycle, it)
        }
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

    override fun onResume() {
        super.onResume()
        binding.toolbar.root.background.alpha = alpha
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}