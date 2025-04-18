package com.example.ejemplo_login

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class LocalAdapter(private val locales: List<Local>) : RecyclerView.Adapter<LocalAdapter.ViewHolder>() {

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvNombre: TextView = view.findViewById(R.id.tvNombreLocal)
        val tvSubtitulo: TextView = view.findViewById(R.id.tvSubtituloLocal)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val vista = LayoutInflater.from(parent.context).inflate(R.layout.item_local, parent, false)
        return ViewHolder(vista)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val local = locales[position]
        holder.tvNombre.text = local.nombre
        holder.tvSubtitulo.text = local.subtitulo
    }

    override fun getItemCount(): Int = locales.size
}
