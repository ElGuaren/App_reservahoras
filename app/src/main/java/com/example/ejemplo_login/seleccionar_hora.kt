package com.example.ejemplo_login

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.GridLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class SeleccionarHoraActivity : AppCompatActivity() {

    private var horaSeleccionada: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_seleccionar_hora)

        val grid = findViewById<GridLayout>(R.id.gridHoras)
        val btnReservar = findViewById<Button>(R.id.btnReservarHora)
        val nombreBarberia = findViewById<TextView>(R.id.tvNombreBarberia)

        // Recibir el nombre de la barbería desde el Intent
        val nombreLocal = intent.getStringExtra("nombre_local")
        nombreBarberia.text = nombreLocal ?: "Barbería"

        // Activar selección de hora
        for (i in 0 until grid.childCount) {
            val btn = grid.getChildAt(i) as Button
            btn.setOnClickListener {
                // Desmarcar todos los botones
                for (j in 0 until grid.childCount) {
                    grid.getChildAt(j).isSelected = false
                }
                // Marcar el botón seleccionado
                btn.isSelected = true
                horaSeleccionada = btn.text.toString()
            }
        }

        // Botón para reservar la hora
        btnReservar.setOnClickListener {
            if (horaSeleccionada != null) {
                // Si hay una hora seleccionada, mostrar un mensaje de confirmación
                Toast.makeText(this, "Hora reservada: $horaSeleccionada", Toast.LENGTH_SHORT).show()
                finish() // Volver a la pantalla anterior (AgendarHorasActivity)
            } else {
                // Si no se seleccionó ninguna hora
                Toast.makeText(this, "Selecciona una hora", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
