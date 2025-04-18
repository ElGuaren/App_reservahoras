package com.example.ejemplo_login

import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class PerfilActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private val db = Firebase.firestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_perfil)

        val btnVolver = findViewById<ImageButton>(R.id.btnVolver)
        val tvNombre = findViewById<TextView>(R.id.tvNombre)
        val tvCorreo = findViewById<TextView>(R.id.tvCorreo)
        val tvTelefono = findViewById<TextView>(R.id.tvTelefono)
        val etTelefono = findViewById<EditText>(R.id.etTelefono)
        val btnGuardar = findViewById<Button>(R.id.btnGuardarTelefono)
        val imgPerfil = findViewById<ImageView>(R.id.imgPerfil)

        auth = FirebaseAuth.getInstance()
        val user = auth.currentUser

        user?.let {
            tvNombre.text = it.displayName ?: "Sin nombre"
            tvCorreo.text = it.email ?: "Sin correo"

            // Cargar imagen de perfil si está disponible
            it.photoUrl?.let { photoUri ->
                Glide.with(this).load(photoUri).into(imgPerfil)
            }

            // Buscar teléfono en Firestore
            db.collection("usuarios").document(it.uid).get().addOnSuccessListener { document ->
                if (document.exists()) {
                    val telefono = document.getString("telefono")
                    if (!telefono.isNullOrBlank()) {
                        tvTelefono.text = telefono
                        etTelefono.visibility = View.GONE
                        btnGuardar.visibility = View.GONE
                    } else {
                        tvTelefono.text = "No disponible"
                    }
                }
            }
        }

        // Guardar teléfono
        btnGuardar.setOnClickListener {
            val telefonoIngresado = etTelefono.text.toString().trim()
            if (telefonoIngresado.isNotEmpty()) {
                val data = hashMapOf("telefono" to telefonoIngresado)
                user?.let {
                    db.collection("usuarios").document(it.uid)
                        .set(data)
                        .addOnSuccessListener {
                            Toast.makeText(this, "Teléfono guardado", Toast.LENGTH_SHORT).show()

                            // Oculta campo y botón, y actualiza el TextView
                            tvTelefono.text = telefonoIngresado
                            etTelefono.visibility = View.GONE
                            btnGuardar.visibility = View.GONE
                        }
                        .addOnFailureListener {
                            Toast.makeText(this, "Error al guardar", Toast.LENGTH_SHORT).show()
                        }
                }
            } else {
                Toast.makeText(this, "Ingresa un número válido", Toast.LENGTH_SHORT).show()
            }
        }

        btnVolver.setOnClickListener {
            finish()
        }
    }
}
