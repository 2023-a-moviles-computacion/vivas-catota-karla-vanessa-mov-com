package com.example.deber3_recyclerview_instagram

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
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
        val btn_add: ImageView
        init {
            nombre = view.findViewById(R.id.tv_storie)
            imagen = view.findViewById(R.id.iv_storie)
            btn_add = view.findViewById(R.id.btn_add) // Asigna el ImageButton
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
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val itemFeed = this.lista[position]

        if (position == 0) {
            // Muestra el ImageButton solo en el primer elemento
            holder.btn_add.setVisibility(View.VISIBLE);
        } else {
            // Oculta el ImageButton en otros elementos
            holder.btn_add.setVisibility(View.GONE);
        }

        holder.nombre.text = itemFeed.nombre
        Glide.with(holder.imagen.context).load(itemFeed.imagen).into(holder.imagen)
    }
    // tamano del arreglo
    override fun getItemCount(): Int {
        return this.lista.size
    }
}