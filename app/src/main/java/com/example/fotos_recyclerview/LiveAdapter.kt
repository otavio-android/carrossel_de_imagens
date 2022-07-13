package com.example.fotos_recyclerview

import android.content.Context
import android.net.Uri
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintSet
import androidx.core.net.toUri
import com.bumptech.glide.Glide
import com.bumptech.glide.Glide.*
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.fotos.view.*


class LiveAdapter(lista:ArrayList<String> ): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var items: List<String> = ArrayList()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return LiveViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.fotos, parent, false)
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {


        when (holder) {
            is LiveViewHolder -> {
                holder.bind(items[position])
            }
        }

    }

    override fun getItemCount(): Int {
        return items.size
    }

   public fun setList(liveList: List<String>) {

        this.items = liveList
    }

    class LiveViewHolder constructor(
        itemView: View
    ) : RecyclerView.ViewHolder(itemView) {

         var Thumbnail = itemView.fotos_usuario

        fun bind(live: String) {

       Thumbnail.setImageURI(live.toUri())

        }

    }
}