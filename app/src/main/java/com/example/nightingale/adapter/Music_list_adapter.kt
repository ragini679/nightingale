package com.example.nightingale.adapter

import android.app.Activity
import android.media.MediaPlayer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.core.net.toUri
import androidx.recyclerview.widget.RecyclerView
import com.example.nightingale.Data
import com.example.nightingale.R
import com.squareup.picasso.NetworkPolicy
import com.squareup.picasso.Picasso

class music_list_adapter(val context: Activity, val dataList: List<Data>):RecyclerView.Adapter<music_list_adapter.myviewholder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): myviewholder {
           val itemView=LayoutInflater.from(context).inflate(R.layout.str_music_list,parent,false)
        return myviewholder(itemView)
    }

    override fun getItemCount(): Int {
      return dataList.size;
    }

    override fun onBindViewHolder(holder: myviewholder, position: Int) {
        val currentdata=dataList[position]
        val mediaPlayer=MediaPlayer.create(context,currentdata.preview.toUri())
        Picasso.get().load(currentdata.album.cover)
            .resize(300, 300)
            .centerCrop()
            .into(holder.image)
        holder.m_name.text=currentdata.title
        holder.m_singer.text=currentdata.artist.name
        holder.m_pausebtn.setOnClickListener(){
            mediaPlayer.start()
        }
        holder.m_backbtn.setOnClickListener(){
            mediaPlayer.stop()
        }

    }
    class myviewholder(itemView: View):RecyclerView.ViewHolder(itemView)  {
          val image:ImageView
          val m_name:TextView
        val m_singer:TextView
        val m_backbtn:ImageButton
        val m_pausebtn:ImageButton
        val m_forwordbtn:ImageButton
        init {
            image=itemView.findViewById(R.id.musiclist_img)
            m_name=itemView.findViewById(R.id.m_name_text)
            m_singer=itemView.findViewById(R.id.m_singer_text)
            m_backbtn=itemView.findViewById(R.id.m_beckbtn)
            m_pausebtn=itemView.findViewById(R.id.m_pausebtn)
            m_forwordbtn=itemView.findViewById(R.id.m_forwordbtn)
        }
    }
}