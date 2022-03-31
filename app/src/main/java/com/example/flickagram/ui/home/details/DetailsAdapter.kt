package com.example.flickagram.ui.home.details

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.flickagram.domain.model.Photo

class DetailsAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {

    private val fragments = ArrayList<Fragment>()

    override fun getItemCount(): Int = fragments.size

    override fun createFragment(position: Int): Fragment = fragments[position]

    fun addFragments(photos: List<Photo>) {
        val currentItemsCount = fragments.size
        if (photos.size != currentItemsCount) {
            val totalItems = photos.size
            photos.subList(currentItemsCount, totalItems).forEachIndexed { index, photo ->
                fragments.add(DetailsListItem.getInstance(photo))
                notifyItemChanged(index)
            }
        }
    }
}
