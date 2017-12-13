package com.kovalenko.marvelgallery.view.main

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.kovalenko.marvelgallery.R
import com.kovalenko.marvelgallery.model.MarvelCharacter
import com.kovalenko.marvelgallery.view.common.ItemAdapter
import com.kovalenko.marvelgallery.view.common.bindView
import com.kovalenko.marvelgallery.view.common.loadImage

class CharacterItemAdapter(
        val character: MarvelCharacter,
        val clicked: (MarvelCharacter) -> Unit
) : ItemAdapter<CharacterItemAdapter.ViewHolder>(R.layout.item_character) {

    override fun onCreateViewHolder(itemView: View) = ViewHolder(itemView)

    override fun ViewHolder.onBindViewHolder() {
        textView.text = character.name
        imageView.loadImage(character.imageUrl)
        itemView.setOnClickListener { clicked(character) }
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
    {
        val textView by bindView<TextView>(R.id.character_name)
        val imageView by bindView<ImageView>(R.id.character_image)
    }
}