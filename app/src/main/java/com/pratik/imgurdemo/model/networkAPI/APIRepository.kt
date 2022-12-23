package com.pratik.imgurdemo.model.networkAPI

import javax.inject.Inject

class APIRepository @Inject constructor(private val apiService: APIService) {
    /*
    *  this function will fetch the image data from the image url
    *  params: query
    * */
    suspend fun getImageData(query: String) = apiService.getImageData(query)
    
}