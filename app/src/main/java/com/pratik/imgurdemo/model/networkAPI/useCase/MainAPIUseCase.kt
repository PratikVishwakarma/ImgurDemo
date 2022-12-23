package com.pratik.imgurdemo.model.networkAPI.useCase

import com.pratik.imgurdemo.model.networkAPI.APIRepository
import com.pratik.imgurdemo.model.networkAPI.ResultData
import com.pratik.imgurdemo.model.networkAPI.responseDTO.ResponseDTO
import javax.inject.Inject

class MainAPIUseCase @Inject constructor(private val apiRepository: APIRepository): ApiUseCase {

    override suspend fun getAllImageData(query: String): ResultData<ResponseDTO> {
        return try {
            val imageData = apiRepository.getImageData(query)
            if (imageData.isSuccessful && imageData.body() != null) ResultData.Success(imageData.body())
            else ResultData.Failed("Something went wrong")
        } catch (e: Exception) {
            e.printStackTrace()
            ResultData.Failed("Something went wrong. Please try again!")
        }
    }
}