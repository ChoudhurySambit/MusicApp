package com.example.myplayer

import android.content.Intent
import android.media.MediaPlayer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.core.net.toUri
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

class MyAdapter (var DataList:List<Data>): RecyclerView.Adapter<MyAdapter.MyViewHolder>() {


    class MyViewHolder(itemView: View):RecyclerView.ViewHolder(itemView) {

        val musicImage:ImageView=itemView.findViewById(R.id.musicImage)
        val artistName: TextView =itemView.findViewById<TextView>(R.id.artistName)
        val time:TextView=itemView.findViewById<TextView>(R.id.duration)
        val musicName:TextView=itemView.findViewById(R.id.musicName)
        val play:ImageView=itemView.findViewById(R.id.btnPlay)
        val pause:ImageView=itemView.findViewById(R.id.btnPause)
        val stop:ImageView=itemView.findViewById(R.id.btnStop)



    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView=LayoutInflater.from(parent.context).inflate(R.layout.each_item,parent,false)

        return MyViewHolder(itemView)

    }

    override fun getItemCount(): Int {
        return DataList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentData= DataList[position]



        holder.musicName.text= currentData.title
        Picasso.get().load(currentData.album.cover).into(holder.musicImage);
        holder.artistName.text= currentData.artist.name
        holder.time.text= currentData.duration.toString()


        val mediaPlayer=MediaPlayer.create(holder.itemView.context,currentData.preview.toUri())

        holder.play.setOnClickListener {

            mediaPlayer.start()

        }

        holder.pause.setOnClickListener {

            mediaPlayer.pause()

        }

        holder.stop.setOnClickListener {

            mediaPlayer.stop()

        }

        holder.itemView.setOnClickListener {

            val intent= Intent(holder.itemView.context,Page2::class.java)

            intent.putExtra("ArtistName", currentData.artist.name.toString())
            intent.putExtra("AlbumImage", currentData.album.cover.toString())
            intent.putExtra("Duration", mediaPlayer.duration.toString())
            intent.putExtra("Music", currentData.preview.toString())
            intent.putExtra("Title", currentData.title.toString())

            holder.itemView.context.startActivity(intent)


        }

    }



}