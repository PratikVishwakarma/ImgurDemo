package com.pratik.imgurdemo.model.networkAPI.useCase

import com.pratik.imgurdemo.model.networkAPI.ResultData
import com.pratik.imgurdemo.model.networkAPI.responseDTO.ResponseDTO

interface ApiUseCase {
    suspend fun getAllImageData(query: String): ResultData<ResponseDTO>
}