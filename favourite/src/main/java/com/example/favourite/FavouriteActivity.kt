package com.example.favourite

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.example.cinebox.di.FavouriteModuleDependencies
import com.example.cinebox.presentation.detail.DetailActivity
import com.example.core.data.Resource
import com.example.core.domain.model.Favourite
import com.example.core.ui.favourite.FavouriteAdapter
import com.example.core.utils.MOVIE_ID
import com.example.core.utils.VerticalItemDecoration
import com.example.core.utils.toPixel
import com.example.favourite.databinding.ActivityFavouriteBinding
import dagger.hilt.android.EntryPointAccessors
import javax.inject.Inject

class FavouriteActivity : AppCompatActivity(), FavouriteAdapter.OnItemClickCallback {

    private var _binding: ActivityFavouriteBinding? = null
    private val binding get() = _binding!!
    private val favouriteAdapter = FavouriteAdapter(this)

    @Inject
    lateinit var factory: ViewModelFactory

    private val favouriteViewModel: FavouriteViewModel by viewModels {
        factory
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        DaggerFavouriteComponent.builder()
            .context(this)
            .appDependencies(
                EntryPointAccessors.fromApplication(
                    applicationContext,
                    FavouriteModuleDependencies::class.java
                )
            )
            .build()
            .inject(this)

        super.onCreate(savedInstanceState)
        _binding = ActivityFavouriteBinding.inflate(layoutInflater)
        setContentView(binding.root)

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
            addItemDecoration(VerticalItemDecoration(margin.toPixel(applicationContext)))
        }
    }

    private fun observeData() {
        favouriteViewModel.getAllFavourite().observe(this) { resource ->
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
        val intent = Intent(applicationContext, DetailActivity::class.java)
        intent.putExtra(MOVIE_ID, favourite.id)
        startActivity(intent)
    }
}