package com.pratik.imgurdemo.utility

import com.pratik.imgurdemo.model.networkAPI.responseDTO.ImageDTO

interface ImageListener {
    fun onItemClick(position: Int, item: ImageDTO)
}