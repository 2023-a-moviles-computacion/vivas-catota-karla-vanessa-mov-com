package com.example.deber3_recyclerview_instagram

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    var totalLikes = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView: RecyclerView = findViewById(R.id.rv_feed)
        recyclerView.layoutManager = LinearLayoutManager(this)
        inicializarRecyclerView()
    }

    /*
    fun aumentarTotalLikes(){
        totalLikes = totalLikes + 1
       /* val totalLikesTextView = findViewById<TextView>(
            R.id.tv_total_likes
        )
        totalLikesTextView.text = totalLikes.toString()

        */
    }
    */


    fun inicializarRecyclerView(){
        val recyclerView = findViewById<RecyclerView>(R.id.rv_feed)
        val adaptador = RecyclerViewAdaptador(
            this, // Contexto
            BaseDeDatos.arregloFeed, // Arreglo datos
            recyclerView // Recycler view
        )
        recyclerView.adapter = adaptador
        recyclerView.itemAnimator = androidx.recyclerview.widget
            .DefaultItemAnimator()
        recyclerView.layoutManager = androidx.recyclerview.widget
            .LinearLayoutManager(this)
        adaptador.notifyDataSetChanged()
    }
}