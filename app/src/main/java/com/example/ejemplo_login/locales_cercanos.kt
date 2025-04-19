package com.example.ejemplo_login

import android.os.Bundle
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar

class LocalesCercanosActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_locales_cercanos)

        // Botón para volver
        val btnVolver = findViewById<ImageButton>(R.id.btnVolverLocales)
        btnVolver.setOnClickListener {
            finish() // Vuelve a la pantalla anterior
        }
    }
}
