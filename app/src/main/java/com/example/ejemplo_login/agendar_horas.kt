package com.example.ejemplo_login

import android.os.Bundle
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class AgendarHorasActivity : AppCompatActivity() {

    private lateinit var recyclerLocales: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_agendar_horas)

        recyclerLocales = findViewById(R.id.recyclerLocales)
        recyclerLocales.layoutManager = LinearLayoutManager(this)

        val listaLocales = listOf(
            Local("Barbería El Clásico", "Horas disponibles"),
            Local("Corte Urbano", "Horas disponibles"),
            Local("Estilo & Afeitado", "Horas disponibles"),
            Local("Barbería Moderna", "Horas disponibles")
        )

        recyclerLocales.adapter = LocalAdapter(listaLocales)

        val btnVolver = findViewById<ImageButton>(R.id.btnVolverAgendar)
        btnVolver.setOnClickListener {
            finish() // vuelve a la pantalla anterior
        }

    }
}
