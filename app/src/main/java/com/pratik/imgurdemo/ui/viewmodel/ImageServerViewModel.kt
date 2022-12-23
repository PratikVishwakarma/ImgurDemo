package com.pratik.imgurdemo.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pratik.imgurdemo.model.networkAPI.ResultData
import com.pratik.imgurdemo.model.networkAPI.responseDTO.ImageDTO
import com.pratik.imgurdemo.model.networkAPI.useCase.ApiUseCase
import com.pratik.imgurdemo.ui.adapter.ImageAdapter
import com.pratik.imgurdemo.utility.printLog
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

/*
* The view model for the image list view
* */

@HiltViewModel
open class ImageServerViewModel @Inject constructor(
    private val apiUseCase: ApiUseCase,
) : ViewModel() {

    val imageList: MutableLiveData<ArrayList<ImageDTO>> = MutableLiveData()
    val isLoading: MutableLiveData<Boolean> = MutableLiveData(false)
    val mImageList = ArrayList<ImageDTO>()
    val searchText: MutableLiveData<String> = MutableLiveData("")
    var selected: MutableLiveData<Int> = MutableLiveData(ImageAdapter.LIST_MODE)

    /*
    * to set the search text value
    * @param: text = the new value
    * */
    fun setSearchText(text: String) {
        searchText.value = text
    }

    /*
     * to set the search text value
     * @param: text = the new value
     * */
    fun switchViewMode() {
        if (selected.value == ImageAdapter.LIST_MODE)
            selected.value = ImageAdapter.GRID_MODE
        else
            selected.value = ImageAdapter.LIST_MODE
    }

    /*
    * this method will start the fetching of image according to the searchText
    * */
    fun getTopImageOfWeek() {
        if (searchText.value.isNullOrEmpty())
            return
        searchText.value?.let { str ->
            isLoading.value = true
            viewModelScope.launch(Dispatchers.IO) {
                when (val allImageDat = apiUseCase.getAllImageData(str)) {
                    is ResultData.Success -> {
                        allImageDat.data?.let {
                            mImageList.clear()
                            mImageList.addAll(it.images)
                            "getTopImageOfWeek images count: ${it.images.size}".printLog()
                            withContext(Dispatchers.Main) {
                                imageList.value = mImageList
                                isLoading.value = false
                            }
                        }
                    }
                    is ResultData.Failed -> {
                        isLoading.postValue(false)
                    }
                }
            }
        }
    }
}