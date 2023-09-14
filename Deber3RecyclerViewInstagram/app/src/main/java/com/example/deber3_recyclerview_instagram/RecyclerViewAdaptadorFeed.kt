package com.example.deber3_recyclerview_instagram

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class RecyclerViewAdaptadorFeed (

    private val contexto: MainActivity,
    private val lista: ArrayList<Feed>,
    private val recyclerView: RecyclerView
): RecyclerView.Adapter<RecyclerViewAdaptadorFeed.MyViewHolder>(){
    inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val imgUser: ImageView
        val user: TextView
        val user2: TextView
        val descripcion: TextView
        val hora: TextView
        val likes: TextView
        val imagen: ImageView

        var numeroLikes = 0
        init {
            user = view.findViewById(R.id.tv_user)
            imgUser = view.findViewById(R.id.iv_user)
            user2 = view.findViewById(R.id.tv_nombre)
            descripcion = view.findViewById(R.id.tv_descripcion)
            hora = view.findViewById(R.id.tv_hora)
            likes = view.findViewById(R.id.tv_numero)
            imagen = view.findViewById(R.id.iv_feed)
           // likeBoton = view.findViewById<Button>(R.id.btn_like)
           // likeBoton.setOnClickListener { anadirLike() }
        }



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

        holder.user.text = itemFeed.user
        Glide.with(holder.imgUser.context).load(itemFeed.imgUser).into(holder.imgUser)
        holder.user2.text = itemFeed.user
        holder.descripcion.text = itemFeed.descripcion
        holder.likes.text = itemFeed.likes
        Glide.with(holder.imagen.context).load(itemFeed.imagen).into(holder.imagen)
        holder.hora.text = itemFeed.hora
    }

    // tamano del arreglo
    override fun getItemCount(): Int {
        return this.lista.size
    }

}
