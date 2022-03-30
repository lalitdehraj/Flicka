package com.example.flickagram.ui.home.photos

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.flickagram.R
import com.example.flickagram.databinding.FragmentPhotosBinding
import com.example.flickagram.ui.home.viewmodels.FetchStatus
import com.example.flickagram.ui.home.viewmodels.MainViewModel
import com.example.flickagram.utils.view.InfiniteScrollListener
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch


@AndroidEntryPoint
class Photos : Fragment(R.layout.fragment_photos) {

    private lateinit var binding: FragmentPhotosBinding
    private val mainViewModel by activityViewModels<MainViewModel>()

    private val photosAdapter by lazy { PhotosAdapter() }
    private val linearLayoutManager by lazy { LinearLayoutManager(requireContext()) }
    private val infiniteScrollListener by lazy {
        InfiniteScrollListener(linearLayoutManager, work = {
            mainViewModel.getPhotos()
        })
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentPhotosBinding.bind(view)
        binding.lifecycleOwner = this


        binding.photosList.apply {
            adapter = photosAdapter
            layoutManager = linearLayoutManager
            addOnScrollListener(infiniteScrollListener)
        }

        attachObservers()
        mainViewModel.getPhotos()
    }

    private fun attachObservers() {
        lifecycleScope.launchWhenStarted {
            launch {
                mainViewModel.fetchStatus.collect {
                    when (it) {
                        FetchStatus.LOADING -> {
                            if (mainViewModel.photoList.value.isNullOrEmpty())
                                binding.emptyListLabel.visibility = View.VISIBLE
                        }

                        FetchStatus.FAILURE -> {
                            if (mainViewModel.photoList.value.isNullOrEmpty())
                                binding.emptyListLabel.visibility = View.VISIBLE
                        }

                        FetchStatus.SUCCESS -> Unit
                        null -> Unit
                    }
                }
            }

            launch {
                mainViewModel.photoList.collect {
                    photosAdapter.submitList(it)
                    binding.emptyListLabel.visibility =
                        if (it.isEmpty()) View.VISIBLE else View.GONE
                }
            }
        }
    }

}