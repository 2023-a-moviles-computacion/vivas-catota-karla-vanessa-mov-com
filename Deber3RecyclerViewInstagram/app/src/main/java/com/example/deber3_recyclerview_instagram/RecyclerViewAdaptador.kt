package com.example.deber3_recyclerview_instagram

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class RecyclerViewAdaptador (

    private val contexto: MainActivity,
    private val lista: ArrayList<Feed>,
    private val recyclerView: RecyclerView
): RecyclerView.Adapter<RecyclerViewAdaptador.MyViewHolder>(){
    inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val nombre: TextView
        val descripcion: TextView
        val numero: TextView
        val imagen: ImageView
        init {
            nombre = view.findViewById(R.id.tv_nombre)
            descripcion = view.findViewById(R.id.tv_descripcion)
            numero = view.findViewById(R.id.tv_numero)
            imagen = view.findViewById(R.id.iv_feed)
        }

        /*
        fun anadirLike(){
            numeroLikes =  numeroLikes + 1
            likesTextView.text = numeroLikes.toString()
            contexto.aumentarTotalLikes()
        }

         */

    }

    // Setear el layout que vamos a utilizar
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater
            .from(parent.context)
            .inflate(
                R.layout.item_feed,
                parent,
                false
            )
        return MyViewHolder(itemView)
    }
    // Setear los datos para la iteracion
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val itemFeed = this.lista[position]

        holder.nombre.text = itemFeed.nombre
        holder.descripcion.text = itemFeed.descripcion
        holder.numero.text = itemFeed.numero.toString()
        Glide.with(holder.imagen.context).load(itemFeed.imagen).into(holder.imagen)

    }

    // tamano del arreglo
    override fun getItemCount(): Int {
        return this.lista.size
    }

}
