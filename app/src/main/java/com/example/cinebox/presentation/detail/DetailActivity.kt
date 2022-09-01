package com.example.cinebox.presentation.detail

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.cinebox.BuildConfig
import com.example.cinebox.R
import com.example.cinebox.core.data.Resource
import com.example.cinebox.core.domain.model.Detail
import com.example.cinebox.databinding.ActivityDetailBinding
import com.example.cinebox.presentation.detail.adapter.CastAdapter
import com.example.cinebox.presentation.detail.adapter.ProductionAdapter
import com.example.cinebox.utils.*
import com.faltenreich.skeletonlayout.Skeleton
import com.faltenreich.skeletonlayout.applySkeleton
import com.faltenreich.skeletonlayout.createSkeleton
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailActivity : AppCompatActivity() {

    private var _binding: ActivityDetailBinding? = null
    private val binding get() = _binding!!
    private val detailViewModel: DetailViewModel by viewModels()
    private val movieId get() = intent.getStringExtra(MOVIE_ID)
    private val castAdapter = CastAdapter()
    private val productionAdapter = ProductionAdapter()
    private var detailMovie: Detail? = null
    private lateinit var detailSkeleton: Skeleton
    private lateinit var rvCastSkeleton: Skeleton
    private lateinit var rvProductionSkeleton: Skeleton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        init()
    }

    private fun init() {
        setToolbar()
        setAdapter()
        setRvSkeleton()
        observeData()
    }

    private fun setToolbar() {
        binding.toolbar.setNavigationOnClickListener {
            finish()
        }
    }

    private fun setAdapter() {
        with(binding.content.rvCast) {
            val margin = 16
            setHasFixedSize(true)
            adapter = castAdapter
            addItemDecoration(HorizontalItemDecoration(margin.toPixel(this@DetailActivity)))
        }

        with(binding.content.rvCompany) {
            val margin = 16
            setHasFixedSize(true)
            adapter = productionAdapter
            addItemDecoration(VerticalItemDecoration(margin.toPixel(this@DetailActivity)))
        }
    }

    private fun observeData() {
        movieId?.let { id ->
            detailViewModel.getDetailMovie(id).observe(this) { resource ->
                when (resource) {
                    is Resource.Success -> {
                        resource.data?.let {
                            detailMovie = it
                            setDetail(it)
                            cancelDetailSkeleton()
                            cancelRvCastSkeleton()
                        }
                    }

                    is Resource.Loading -> {
                        showDetailSkeleton()
                        showRvCastSkeleton()
                    }

                    is Resource.Error -> {
                        showDetailSkeleton()
                        showRvCastSkeleton()
                    }
                }
            }

            detailViewModel.getCreditMovie(id).observe(this) { resource ->
                when (resource) {
                    is Resource.Success -> {
                        resource.data?.let {
                            castAdapter.submitList(it.cast)
                            cancelRvProductionSkeleton()
                        }
                    }

                    is Resource.Loading -> showRvProductionSkeleton()


                    is Resource.Error -> showRvProductionSkeleton()

                }
            }

            detailViewModel.isFavourite(id).observe(this) { isFavourite ->

                with(binding.fab) {
                    if (isFavourite) {
                        setImageDrawable(this@DetailActivity.getHelperDrawable(R.drawable.ic_baseline_favorite_24))
                    } else {
                        setImageDrawable(this@DetailActivity.getHelperDrawable(R.drawable.ic_baseline_favorite_border_24))
                    }

                    setOnClickListener {
                        if (!isFavourite) {
                            detailMovie?.let {
                                detailViewModel.insertFavourite(DataMapper.mapDetailToFavourite(it))
                            }
                        } else {
                            detailViewModel.deleteFavouriteById(id)
                        }
                    }
                }

            }

        }
    }

    private fun setDetail(detail: Detail) {

        productionAdapter.submitList(detail.productionCompanies)

        Glide.with(this)
            .load(BuildConfig.BASE_URL_POSTER_ORIGINAL + detail.backdropPath)
            .into(binding.ivBackdrop)

        with(binding.content) {

            Glide.with(this@DetailActivity)
                .load(BuildConfig.BASE_URL_POSTER_W500 + detail.posterPath)
                .into(ivPoster)

            tvTitle.text = detail.title

            val genres = arrayListOf<String>()
            detail.genres.map {
                genres.add(it.name)
            }

            tvGenre.text = genres.joinToString(", ")
            tvPopularity.text = detail.popularity.toString()
            tvRelease.text = detail.releaseDate
            tvRating.text = detail.voteAverage.toString()
            tvOverview.text = detail.overview

        }

    }

    private fun showDetailSkeleton() {
        val radius = 16

        detailSkeleton = binding.content.skeleton.createSkeleton()
        detailSkeleton.maskCornerRadius = radius.toPixel(this).toFloat()

        detailSkeleton.showSkeleton()
    }

    private fun cancelDetailSkeleton() {
        detailSkeleton.showOriginal()
    }

    private fun setRvSkeleton() {
        val radius = 16

        with(binding.content) {
            rvCastSkeleton = rvCast.applySkeleton(R.layout.item_row_cast)
            rvProductionSkeleton = rvCompany.applySkeleton(R.layout.item_row_production)
        }
        rvCastSkeleton.maskCornerRadius = radius.toPixel(this).toFloat()
        rvProductionSkeleton.maskCornerRadius = radius.toPixel(this).toFloat()
    }

    private fun showRvCastSkeleton() {
        rvCastSkeleton.showSkeleton()
    }

    private fun showRvProductionSkeleton() {
        rvProductionSkeleton.showSkeleton()
    }

    private fun cancelRvCastSkeleton() {
        rvCastSkeleton.showOriginal()
    }

    private fun cancelRvProductionSkeleton() {
        rvProductionSkeleton.showOriginal()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}