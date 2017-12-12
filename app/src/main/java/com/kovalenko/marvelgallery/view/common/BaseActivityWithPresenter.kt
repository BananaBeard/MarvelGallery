package com.kovalenko.marvelgallery.view.common

import android.support.v7.app.AppCompatActivity
import com.kovalenko.marvelgallery.presenter.Presenter

abstract class BaseActivityWithPresenter : AppCompatActivity() {

    abstract val presenter: Presenter

    override fun onDestroy() {
        super.onDestroy()
        presenter.onViewDestroyed()
    }
}