package com.kovalenko.marvelgallery.data.network.dto

class ImageDto {

    lateinit var path: String
    lateinit var extension: String

    val completeImagepath: String
        get() = "$path.$extension"
}