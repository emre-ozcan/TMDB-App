package com.emreozcan.tmdbapp.ui.home

import android.view.inputmethod.EditorInfo
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.emreozcan.tmdbapp.base.BaseFragment
import com.emreozcan.tmdbapp.databinding.FragmentHomeBinding
import com.emreozcan.tmdbapp.ui.home.adapter.ItemClickListener
import com.emreozcan.tmdbapp.ui.home.adapter.MovieAdapter
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding, HomeViewModel>(
    FragmentHomeBinding::inflate
) {
    override val viewModel by viewModels<HomeViewModel>()

    override fun onCreateFinished() {
        setDataBinding()
        binding.lifecycleOwner = this
    }

    override fun initializeListeners() {
        binding.eTSearch.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                viewModel.getSearchMovies(binding.eTSearch.text.toString().trim())
                hideKeyboard()
            }
            true
        }
        binding.textInputLayout.setEndIconOnClickListener {
            binding.eTSearch.text?.clear()
            binding.textInputLayout.clearFocus()
            viewModel.getPopularMovies()
        }
    }

    override fun observeEvents() {
        with(viewModel) {
            moviesList.observe(viewLifecycleOwner) { popularMovies ->
                if (popularMovies?.movies.isNullOrEmpty()) {
                    movieAdapter?.deleteList()
                    binding.tvEmptyMovie.isVisible = true
                } else {
                    popularMovies?.movies?.let {
                        binding.rvHome.scheduleLayoutAnimation()
                        viewModel.movieAdapter?.setList(it)
                        binding.tvEmptyMovie.isVisible = false
                    }
                }
            }
            isLoading.observe(viewLifecycleOwner) {
                binding.progressBar.isVisible = it
            }
            onError.observe(viewLifecycleOwner) {
                it?.let { it1 -> showToast(it1) }
            }
        }
    }

    private fun setDataBinding() {
        viewModel.movieAdapter = MovieAdapter(object : ItemClickListener {
            override fun onItemClick(movieId: String) {
                val navigation = HomeFragmentDirections.actionHomeFragmentToDetailFragment(movieId)
                Navigation.findNavController(requireView()).navigate(navigation)
            }
        })
        viewModel.movieAdapter?.let {
            binding.adapter = it
        }
    }
}