package com.example.ejemplo_login

import com.example.ejemplo_login.LocalAdapter
import android.content.Intent
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

        // Lista de locales
        val listaLocales = listOf(
            Local("Barbería El Clásico", "Horas disponibles"),
            Local("Corte Urbano", "Horas disponibles"),
            Local("Estilo & Afeitado", "Horas disponibles"),
            Local("Barbería Moderna", "Horas disponibles")
        )

        // Crear el adaptador y pasar el click listener
        recyclerLocales.adapter = LocalAdapter(listaLocales) { local ->
            // Al hacer clic en un local, redirige a la pantalla de seleccionar hora
            val intent = Intent(this, SeleccionarHoraActivity::class.java)
            intent.putExtra("nombre_local", local.nombre) // Pasamos el nombre del local
            startActivity(intent)
        }

        // Botón para volver a la pantalla anterior
        val btnVolver = findViewById<ImageButton>(R.id.btnVolverAgendar)
        btnVolver.setOnClickListener {
            finish() // Vuelve a la pantalla anterior
        }
    }
}
