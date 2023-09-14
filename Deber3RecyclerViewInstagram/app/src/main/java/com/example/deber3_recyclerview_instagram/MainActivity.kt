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

        val recyclerViewFeed: RecyclerView = findViewById(R.id.rv_feed)
        recyclerViewFeed.layoutManager = LinearLayoutManager(this)
        val recyclerViewStorie: RecyclerView = findViewById(R.id.rv_storie)
        recyclerViewStorie.layoutManager = LinearLayoutManager(this)
        inicializarRecyclerView()
    }

    fun inicializarRecyclerView(){
        val recyclerViewFeed = findViewById<RecyclerView>(R.id.rv_feed)
        val adaptadorFeed = RecyclerViewAdaptadorFeed(
            this, // Contexto
            BaseDeDatos.arregloFeed, // Arreglo datos
            recyclerViewFeed // Recycler view
        )
        recyclerViewFeed.adapter = adaptadorFeed
        recyclerViewFeed.itemAnimator = androidx.recyclerview.widget
            .DefaultItemAnimator()
        recyclerViewFeed.layoutManager = androidx.recyclerview.widget
            .LinearLayoutManager(this)
        adaptadorFeed.notifyDataSetChanged()

        val recyclerViewStorie = findViewById<RecyclerView>(R.id.rv_storie)
        val adaptadorStorie = RecyclerViewAdaptadorStorie(
            this, // Contexto
            BaseDeDatos.arregloStories, // Arreglo datos
            recyclerViewStorie // Recycler view
        )

        recyclerViewStorie.adapter = adaptadorStorie
        recyclerViewStorie.itemAnimator = androidx.recyclerview.widget
            .DefaultItemAnimator()
        recyclerViewStorie.layoutManager = androidx.recyclerview.widget
            .LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false )
        adaptadorStorie.notifyDataSetChanged()
    }
}