package com.example.cinebox.presentation.favourite

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.cinebox.R
import com.example.cinebox.databinding.FragmentFavoriteBinding
import com.example.cinebox.presentation.detail.DetailActivity
import com.example.core.data.Resource
import com.example.core.domain.model.Favourite
import com.example.core.ui.favourite.FavouriteAdapter
import com.example.core.utils.MOVIE_ID
import com.example.core.utils.VerticalItemDecoration
import com.example.core.utils.toPixel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavouriteFragment : Fragment(), FavouriteAdapter.OnItemClickCallback {

    private var _binding: FragmentFavoriteBinding? = null
    private val binding get() = _binding!!
    private val favouriteViewModel: FavouriteViewModel by viewModels()
    private val favouriteAdapter = FavouriteAdapter(this)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentFavoriteBinding.inflate(layoutInflater)
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

    private fun setToolbar() {
        binding.toolbar.tvTitle.text = getString(R.string.favourite_movie)
    }

    private fun setAdapter() {
        with(binding.rvFavorite) {
            val margin = 24
            setHasFixedSize(true)
            adapter = favouriteAdapter
            addItemDecoration(VerticalItemDecoration(margin.toPixel(requireContext())))
        }
    }

    private fun observeData() {
        favouriteViewModel.getAllFavourite().observe(viewLifecycleOwner) { resource ->
            when (resource) {
                is Resource.Success -> {
                    favouriteAdapter.submitList(resource.data)
                    binding.tvEmpty.isVisible = false
                }

                else -> {
                    favouriteAdapter.submitList(null)
                    binding.tvEmpty.isVisible = true
                }

            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun onItemClicked(favourite: Favourite) {
        val intent = Intent(requireContext(), DetailActivity::class.java)
        intent.putExtra(MOVIE_ID, favourite.id)
        startActivity(intent)
    }

}