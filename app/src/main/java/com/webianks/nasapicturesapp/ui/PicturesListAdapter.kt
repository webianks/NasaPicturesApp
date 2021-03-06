package com.webianks.nasapicturesapp.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.webianks.nasapicturesapp.data.NasaPicture
import com.webianks.nasapicturesapp.databinding.ItemListPictureBinding

class PicturesListAdapter(
    private val list: List<NasaPicture>,
    private val openDetailsClickLister: ((Int) -> Unit)? = null
) :
    RecyclerView.Adapter<PicturesListViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PicturesListViewHolder {
        val itemBinding =
            ItemListPictureBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PicturesListViewHolder(itemBinding, openDetailsClickLister)
    }

    override fun onBindViewHolder(holder: PicturesListViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int = list.size
}