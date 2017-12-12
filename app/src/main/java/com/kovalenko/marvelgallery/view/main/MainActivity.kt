package com.kovalenko.marvelgallery.view.main

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.widget.GridLayoutManager
import android.view.Window
import com.kovalenko.marvelgallery.R
import com.kovalenko.marvelgallery.data.MarvelRepository
import com.kovalenko.marvelgallery.model.MarvelCharacter
import com.kovalenko.marvelgallery.presenter.MainPresenter
import com.kovalenko.marvelgallery.presenter.Presenter
import com.kovalenko.marvelgallery.view.character.CharacterProfileActivity
import com.kovalenko.marvelgallery.view.common.BaseActivityWithPresenter
import com.kovalenko.marvelgallery.view.common.addOnTextChangedListener
import com.kovalenko.marvelgallery.view.common.bindToSwipeRefresh
import com.kovalenko.marvelgallery.view.common.toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivityWithPresenter(), MainView {

    override var refresh by bindToSwipeRefresh(R.id.swipeRefreshView)

    override val presenter by lazy { MainPresenter(this, MarvelRepository.get()) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.activity_main)

        recyclerView.layoutManager = GridLayoutManager(this, 2)
        swipeRefreshView.setOnRefreshListener {
            presenter.onRefresh()
        }

        swipeRefreshView.setColorSchemeColors(ContextCompat.getColor(this, R.color.colorPrimary))

        searchView.addOnTextChangedListener {
            onTextChanged { text, _, _, _ ->
                presenter.onSearchChanged(text)
            }
        }

        presenter.onViewCreated()
    }

    override fun show(items: List<MarvelCharacter>) {
        val categoryItemAdapters = items.map(this::createCategoryItemAdapter)
        recyclerView.adapter = MainListAdapter(categoryItemAdapters)
    }

    override fun showError(error: Throwable) {
        toast("Error: ${error.message}")
        error.printStackTrace()
    }

    private fun createCategoryItemAdapter(character: MarvelCharacter)
            = CharacterItemAdapter(character, { showHeroProfile(character) })

    private fun showHeroProfile(character: MarvelCharacter) {
        CharacterProfileActivity.start(this, character)
    }
}
