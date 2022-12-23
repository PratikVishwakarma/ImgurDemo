package com.pratik.imgurdemo.model.networkAPI.useCase

import com.pratik.imgurdemo.model.networkAPI.APIRepository
import com.pratik.imgurdemo.model.networkAPI.ResultData
import com.pratik.imgurdemo.model.networkAPI.responseDTO.ImageDTO
import com.pratik.imgurdemo.model.networkAPI.responseDTO.ResponseDTO
import javax.inject.Inject

open class TestAPIUseCase @Inject constructor(private val apiRepository: APIRepository) : ApiUseCase {

    override suspend fun getAllImageData(query: String): ResultData<ResponseDTO> {
        val dummyImages : ArrayList<ImageDTO> = ArrayList<ImageDTO>().apply { add(ImageDTO())
            add(ImageDTO())
        }

        val responseDTO = ResponseDTO().apply {
            images = dummyImages
        }
        "getAllImageData images: ${responseDTO.images.size}"
        return ResultData.Success(responseDTO)
    }
}