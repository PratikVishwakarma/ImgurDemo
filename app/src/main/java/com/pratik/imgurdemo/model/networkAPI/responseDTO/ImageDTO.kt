package com.pratik.imgurdemo.model.networkAPI.responseDTO

import com.google.gson.annotations.SerializedName
import com.pratik.imgurdemo.utility.getDateByFormat

class ImageDTO {
    @SerializedName("id")
    var id = ""

    @SerializedName("title")
    var title = ""

    @SerializedName("datetime")
    var datetime: Long = System.currentTimeMillis()

    @SerializedName("images")
    var images: List<SingleImageDTO> = ArrayList()

    fun getDateInString(): String {
        return datetime.getDateByFormat()
    }

    fun getTotalImages(): String {
        return (images.size.toString() ?: "") + " image"
    }
}