package com.example.myplayer

import android.annotation.SuppressLint
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.ImageView
import android.widget.SeekBar
import android.widget.TextView
import androidx.core.net.toUri
import com.squareup.picasso.Picasso

class Page2 : AppCompatActivity() {

    lateinit var musicPlayer: MediaPlayer

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_page2)

        musicPlayer=MediaPlayer.create(this,intent.getStringExtra("Music")?.toUri())

        musicPlayer.setVolume(1f,1f)
        musicPlayer.isLooping=true


        val musicImage=findViewById<ImageView>(R.id.musicImage)

//        Picasso.get().load(intent.getStringExtra("AlbumImage")).into(musicImage)

        findViewById<TextView>(R.id.name).text=intent.getStringExtra("ArtistName")

        findViewById<TextView>(R.id.time).text=intent.getStringExtra("Duration")

        findViewById<TextView>(R.id.title).text=intent.getStringExtra("Title")

        val btnPlay=findViewById<ImageView>(R.id.playBtn)

        val btnPause=findViewById<ImageView>(R.id.pauseBtn)

        val btnStop=findViewById<ImageView>(R.id.stopBtn)

        val seekBarMusic=findViewById<SeekBar>(R.id.seekBar)

        btnPlay.setOnClickListener {

            musicPlayer.start()

        }
        btnPause.setOnClickListener {

            musicPlayer.pause()

        }
        btnStop.setOnClickListener {

            musicPlayer.stop()
            musicPlayer.reset()

        }


        seekBarMusic.max=musicPlayer.duration

        seekBarMusic.setOnSeekBarChangeListener(object :SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {

                if(p2){

                    musicPlayer.seekTo(p1)

                }

            }

            override fun onStartTrackingTouch(p0: SeekBar?) {

            }

            override fun onStopTrackingTouch(p0: SeekBar?) {

            }

        })


        val handler=Handler()

        handler.postDelayed(object : Runnable {
            override fun run() {
                seekBarMusic.progress=musicPlayer.currentPosition
                handler.postDelayed(this,1000)
            }

        },0)



    }

    override fun onPause() {
        super.onPause()

        // Pause or stop the media player when leaving the activity
        musicPlayer.pause()
    }

//    override fun onStop() {
//        super.onStop()
//
//        // Release the media player resources when the activity is stopped
//        musicPlayer.release()
//
//    }
}