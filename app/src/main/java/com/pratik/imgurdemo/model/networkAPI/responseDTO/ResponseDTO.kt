package com.pratik.imgurdemo.model.networkAPI.responseDTO

import com.google.gson.annotations.SerializedName

data class ResponseDTO(
    @SerializedName("data")
    val images: List<ImageDTO> = ArrayList(),

    @SerializedName("success")
    val success: Boolean = true,

    @SerializedName("status")
    val status: Int = 0,

    )
