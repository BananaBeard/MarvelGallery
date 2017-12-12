package com.kovalenko.marvelgallery.data

import com.kovalenko.marvelgallery.data.network.MarvelApi
import com.kovalenko.marvelgallery.data.network.providers.retrofit
import com.kovalenko.marvelgallery.model.MarvelCharacter
import io.reactivex.Single

class MarvelRepositoryImpl: MarvelRepository {

    val api = retrofit.create(MarvelApi::class.java)

    override fun getAllCharacters(searchQuery: String?): Single<List<MarvelCharacter>> = api.getCharacters(
            offset = 0,
            searchQuery = searchQuery,
            limit = elementsOnListLimit
    ).map {
        it.data?.results.orEmpty().map(::MarvelCharacter)
    }

    companion object {
        const val elementsOnListLimit = 50
    }
}