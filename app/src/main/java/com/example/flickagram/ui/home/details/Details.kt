package com.example.flickagram.ui.home.details

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.viewpager2.widget.ViewPager2
import com.example.flickagram.R
import com.example.flickagram.databinding.FragmentDetailsBinding
import com.example.flickagram.ui.home.viewmodels.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class Details : Fragment(R.layout.fragment_details) {

    private lateinit var binding: FragmentDetailsBinding
    private val mainViewModel by activityViewModels<MainViewModel>()

    private val detailsAdapter by lazy { DetailsAdapter(this) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentDetailsBinding.bind(view)
        binding.lifecycleOwner = this

        attachPhotoList()
        setUpList()
    }

    private fun attachPhotoList() {
        lifecycleScope.launchWhenStarted {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                mainViewModel.photoList.collect {
                    detailsAdapter.addFragments(it)
                }
            }
        }
    }

    private fun setUpList() {
        val position = requireArguments().getInt("position")
        binding.detailsContainer.adapter = detailsAdapter
        binding.detailsContainer.post {
            binding.detailsContainer.setCurrentItem(position)
        }

        binding.detailsContainer.registerOnPageChangeCallback(object :
            ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                val currentTotalItems = mainViewModel.photoList.value.size
                if (position >= currentTotalItems - 6) mainViewModel.getPhotos()
            }
        })
    }
}