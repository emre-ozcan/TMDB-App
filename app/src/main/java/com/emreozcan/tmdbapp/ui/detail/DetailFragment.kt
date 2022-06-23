package com.emreozcan.tmdbapp.ui.detail

import android.graphics.Outline
import android.view.View
import android.view.ViewOutlineProvider
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import com.emreozcan.tmdbapp.base.BaseFragment
import com.emreozcan.tmdbapp.databinding.FragmentDetailBinding
import com.emreozcan.tmdbapp.model.response.detail.credits.Cast
import com.emreozcan.tmdbapp.ui.detail.adapter.CastAdapter
import com.emreozcan.tmdbapp.ui.detail.adapter.ItemCastClickListener
import com.emreozcan.tmdbapp.utils.Constants.EXAMPLE_VIDEO
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class DetailFragment : BaseFragment<FragmentDetailBinding, DetailViewModel>(
    FragmentDetailBinding::inflate
) {
    override val viewModel by viewModels<DetailViewModel>()
    private val args by navArgs<DetailFragmentArgs>()

    private lateinit var exoPlayer: ExoPlayer

    override fun onCreateFinished() {
        callServices(args.movieId)
        setAdapter()
        setPlayer()

    }

    private fun setPlayer() {
        exoPlayer = ExoPlayer.Builder(requireContext()).build()

        binding.exoPlayerView.player = exoPlayer
        val mediaItem = MediaItem.fromUri(EXAMPLE_VIDEO)

        exoPlayer.apply {
            setMediaItem(mediaItem)
            prepare()
        }

        //Rounded Corners For Player
        binding.exoPlayerView.outlineProvider = object : ViewOutlineProvider() {
            override fun getOutline(view: View, outline: Outline) {
                outline.setRoundRect(0, 0, view.width, view.height, 30f)
            }
        }

        binding.exoPlayerView.clipToOutline = true
    }

    private fun setAdapter() {
        viewModel.adapter = CastAdapter(object : ItemCastClickListener {
            override fun onItemClick(cast: Cast) {
                val action = DetailFragmentDirections.actionDetailFragmentToCastFragment(cast)
                Navigation.findNavController(requireView()).navigate(action)
            }
        })
        binding.adapter = viewModel.adapter
    }

    override fun initializeListeners() {
        with(binding) {
            ivBackDetail.setOnClickListener {
                activity?.onBackPressed()
            }
            btnWatch.setOnClickListener {
                exoPlayer.play()
            }
        }
    }

    override fun observeEvents() {
        with(viewModel) {
            movieDetail.observe(viewLifecycleOwner) {
                it?.let {
                    binding.movie = it
                }
            }
            movieCredits.observe(viewLifecycleOwner) {
                it?.let {
                    it.cast?.let { it1 -> viewModel.adapter?.setList(it1) }
                }
            }
            isLoading.observe(viewLifecycleOwner) {
                binding.progressBar.isVisible = it
                binding.clDetail.isVisible = !it
            }
        }
    }

    private fun callServices(movieId: String) {
        viewModel.getMovieDetail(movieId)
        viewModel.getMovieCredits(movieId)
    }
}