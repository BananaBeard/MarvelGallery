package com.kovalenko.marvelgallery.presenter

import com.kovalenko.marvelgallery.data.MarvelRepository
import com.kovalenko.marvelgallery.data.applySchedulers
import com.kovalenko.marvelgallery.data.plusAssign
import com.kovalenko.marvelgallery.data.subscribeBy
import com.kovalenko.marvelgallery.view.main.MainView

class MainPresenter(
        val view: MainView,
        val repository: MarvelRepository
) : BasePresenter() {

    fun onViewCreated() {
        loadCharacters()
    }

    fun onRefresh() {
        loadCharacters()
    }

    fun onSearchChanged(text: String) {
        loadCharacters(text)
    }

    private fun loadCharacters(searchQuery: String? = null) {

        val qualifiedSearchQuery = if (searchQuery.isNullOrBlank()) null
        else searchQuery

        subscriptions += repository.getAllCharacters(qualifiedSearchQuery)
                .applySchedulers()
                .doOnSubscribe { view.refresh = true }
                .doFinally { view.refresh = false }
                .subscribeBy(
                        onSuccess = view::show,
                        onError = view::showError
                )
    }
}