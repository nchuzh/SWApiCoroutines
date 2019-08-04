package com.nchuzh.swapikotlincoroutines.view.characterlist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.nchuzh.swapikotlincoroutines.R
import com.nchuzh.swapikotlincoroutines.domain.model.MovieCharacter
import kotlinx.android.synthetic.main.item_character.view.*

class CharacterAdapter(private val onClick: (MovieCharacter) -> Unit) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    var data: MutableList<MovieCharacter> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_character, parent, false)
        return ViewHolder(view)
    }

    fun addData(newData: List<MovieCharacter>) {
        data.addAll(newData)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as ViewHolder).initViews(data[position])
    }

    internal inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun initViews(item: MovieCharacter) {
            itemView.txt_name.text = item.name
            itemView.setOnClickListener { onClick.invoke(item) }
        }
    }
}
