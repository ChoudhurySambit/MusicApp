package com.example.myplayer

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Page1 : AppCompatActivity() {

    lateinit var recyclerView:RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_page1)

        recyclerView=findViewById(R.id.recyclerView)

        val retrofitBuilder=Retrofit.Builder()
            .baseUrl("https://deezerdevs-deezer.p.rapidapi.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(Apiinterface::class.java)



        val retrofitData=retrofitBuilder.get("eminem")
        retrofitData.enqueue(object : Callback<MyData?> {

            override fun onResponse(call: Call<MyData?>, response: Response<MyData?>) {
                val dataList: List<Data> = response.body()?.data!!

                recyclerView.layoutManager=LinearLayoutManager(this@Page1)
                recyclerView.adapter= MyAdapter(dataList)

            }

            override fun onFailure(call: Call<MyData?>, t: Throwable) {

                Log.d("TAG Failure","On Failure"+t.message)

            }


        })
    }


}