package com.kovalenko.marvelgallery.view.main

import com.kovalenko.marvelgallery.model.MarvelCharacter

interface MainView {
    var refresh: Boolean
    fun show(items: List<MarvelCharacter>)
    fun showError(error: Throwable)
}