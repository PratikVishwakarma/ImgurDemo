package com.pratik.imgurdemo.model.networkAPI

import com.pratik.imgurdemo.model.networkAPI.NetworkConstant.GALLERY_URL
import com.pratik.imgurdemo.model.networkAPI.responseDTO.ResponseDTO
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface APIService {
    @GET(GALLERY_URL)
    suspend fun getImageData(@Query("q") query: String): Response<ResponseDTO>
}