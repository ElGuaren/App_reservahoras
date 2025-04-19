package com.example.ejemplo_login

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.card.MaterialCardView

class LocalAdapter(
    private val locales: List<Local>,
    private val onClick: (Local) -> Unit
) : RecyclerView.Adapter<LocalAdapter.ViewHolder>() {

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvNombre: TextView = view.findViewById(R.id.tvNombreLocal)
        val tvSubtitulo: TextView = view.findViewById(R.id.tvSubtituloLocal)
        val cardView: MaterialCardView = view.findViewById(R.id.cardLocal)

        init {
            // Asignamos el click listener a la card
            cardView.setOnClickListener {
                val local = locales[adapterPosition]
                onClick(local)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_local, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val local = locales[position]
        holder.tvNombre.text = local.nombre
        holder.tvSubtitulo.text = local.subtitulo
    }

    override fun getItemCount(): Int = locales.size
}
