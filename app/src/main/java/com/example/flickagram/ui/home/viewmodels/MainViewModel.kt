package com.example.flickagram.ui.home.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.flickagram.domain.model.Photo
import com.example.flickagram.domain.sources.MainSource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val mainSource: MainSource) : ViewModel() {
    private var page = 1
    private var hasNextPage = true

    private val _photosList = MutableStateFlow<List<Photo>>(ArrayList())
    val photoList = _photosList.asStateFlow()

    private val _fetchStatus = MutableStateFlow<FetchStatus?>(null)
    val fetchStatus = _fetchStatus.asStateFlow()

    fun getPhotos() {
        if (hasNextPage) {
            viewModelScope.launch {
                _fetchStatus.value = FetchStatus.LOADING
                withContext(Dispatchers.IO) {
                    try {
                        val response = mainSource.getImages(page = page)
                        if (response.isSuccessful){

                            response.body()?.photos?.let { photos ->
                                hasNextPage = photos.totalPagesCount > photos.currentPage
                                page = photos.currentPage + 1

                                val updatePhotosList = ArrayList<Photo>().apply {
                                    addAll(photoList.value)
                                    addAll(photos.photoList)
                                }

                                _photosList.value = updatePhotosList
                                _fetchStatus.value = FetchStatus.SUCCESS
                            }

                        }else{
                            _fetchStatus.value = FetchStatus.FAILURE
                        }
                    }catch (e : Exception) {
                        _fetchStatus.value = FetchStatus.FAILURE
                    }
                }
            }
        }
    }


}
enum class FetchStatus {
    LOADING,
    SUCCESS,
    FAILURE
}