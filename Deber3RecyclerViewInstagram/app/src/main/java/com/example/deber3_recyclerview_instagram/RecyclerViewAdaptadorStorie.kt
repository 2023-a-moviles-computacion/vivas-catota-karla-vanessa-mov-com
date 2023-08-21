package com.example.deber3_recyclerview_instagram

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class RecyclerViewAdaptadorStorie(
    private val contexto: MainActivity,
    private val lista: ArrayList<Storie>,
    private val recyclerView: RecyclerView
) : RecyclerView.Adapter<RecyclerViewAdaptadorStorie.MyViewHolder>() {
    inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val nombre: TextView
        val imagen: ImageView
        init {
            nombre = view.findViewById(R.id.tv_storie)
            imagen = view.findViewById(R.id.iv_storie)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater
            .from(parent.context)
            .inflate(
                R.layout.item_storie,
                parent,
                false
            )
        return MyViewHolder(itemView)
    }
    // Setear los datos para la iteracion
    override fun onBindViewHolder(holder: RecyclerViewAdaptadorStorie.MyViewHolder, position: Int) {
        val itemFeed = this.lista[position]

        holder.nombre.text = itemFeed.nombre
        Glide.with(holder.imagen.context).load(itemFeed.imagen).into(holder.imagen)
    }
    // tamano del arreglo
    override fun getItemCount(): Int {
        return this.lista.size
    }
}