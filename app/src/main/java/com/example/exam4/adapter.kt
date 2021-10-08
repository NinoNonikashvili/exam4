package com.example.exam4

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView


class adapter(private val cells: List<Int>): RecyclerView.Adapter<adapter.itemHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): itemHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.fragment_celllayout, parent, false)
        return itemHolder(itemView)
    }

    override fun onBindViewHolder(holder: itemHolder, position: Int) {
        holder.imageButton.setImageResource(cells[position])

    }


    override fun getItemCount() = cells.size

    inner class itemHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        val imageButton:ImageButton = itemView.findViewById(R.id.ib_cell)

    }

}
