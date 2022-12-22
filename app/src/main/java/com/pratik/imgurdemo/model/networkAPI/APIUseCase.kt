package com.pratik.imgurdemo.model.networkAPI

import com.pratik.imgurdemo.model.networkAPI.responseDTO.ResponseDTO
import javax.inject.Inject

class APIUseCase @Inject constructor(private val apiRepository: APIRepository) {

    suspend fun getAllImageDat(query: String): ResultData<ResponseDTO> {
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