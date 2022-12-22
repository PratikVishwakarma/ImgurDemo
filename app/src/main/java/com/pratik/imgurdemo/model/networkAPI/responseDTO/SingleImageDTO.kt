package com.pratik.imgurdemo.model.networkAPI.responseDTO

import com.google.gson.annotations.SerializedName

class SingleImageDTO {
    @SerializedName("id")
    var id = ""

    @SerializedName("link")
    var link = ""
}