package com.example.ejemplo_login

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.android.material.button.MaterialButton
import com.google.android.material.card.MaterialCardView

class Pantalla1 : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pantalla1)

        auth = FirebaseAuth.getInstance()

        // Ir al perfil
        val btnPerfil = findViewById<ImageButton>(R.id.btnPerfil)
        btnPerfil.setOnClickListener {
            startActivity(Intent(this, PerfilActivity::class.java))
        }

        // Ir a AgendarHorasActivity al presionar la card
        val cardAgendar = findViewById<MaterialCardView>(R.id.cardAgendar)
        cardAgendar.setOnClickListener {
            startActivity(Intent(this, AgendarHorasActivity::class.java))
        }

        // Ir a LocalesCercanosActivity al presionar la card de Locales Cercanos
        val cardLocalesCercanos = findViewById<MaterialCardView>(R.id.cardLocales)
        cardLocalesCercanos.setOnClickListener {
            startActivity(Intent(this, LocalesCercanosActivity::class.java))
        }

        // Cerrar sesión
        val btnCerrarSesion = findViewById<MaterialButton>(R.id.btnCerrarSesion)
        btnCerrarSesion.setOnClickListener {
            auth.signOut()
            Toast.makeText(this, "Sesión cerrada", Toast.LENGTH_SHORT).show()
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
    }
}
