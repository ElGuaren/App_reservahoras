package com.example.ejemplo_login

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.ejemplo_login.databinding.ActivityRegistroBinding
import com.google.firebase.auth.FirebaseAuth

class RegistroActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegistroBinding
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityRegistroBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val tvIniciarSesion = findViewById<TextView>(R.id.tvIniciarSesion)
        tvIniciarSesion.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish() // opcional: cierra la pantalla de registro
        }


        // Inicializar FirebaseAuth
        auth = FirebaseAuth.getInstance()

        // Ajuste de márgenes del sistema
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Configuración del botón de registro
        binding.btnRegistrar.setOnClickListener {
            val email = binding.etEmail.text.toString().trim()
            val password = binding.etPassword.text.toString().trim()
            val confirmPassword = binding.etConfirmPassword.text.toString().trim()

            // Validación de los campos
            if (email.isEmpty()) {
                binding.tilEmail.error = "Ingrese su correo"
                return@setOnClickListener
            }
            if (password.isEmpty()) {
                binding.tilPassword.error = "Ingrese su contraseña"
                return@setOnClickListener
            }
            if (confirmPassword.isEmpty()) {
                binding.tilConfirmPassword.error = "Confirme su contraseña"
                return@setOnClickListener
            }

            // Validar si las contraseñas coinciden
            if (password != confirmPassword) {
                binding.tilConfirmPassword.error = "Las contraseñas no coinciden"
                return@setOnClickListener
            }

            // Llamar al método para registrar al usuario
            registerUser(email, password)
        }
    }

    private fun registerUser(email: String, password: String) {
        auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                // Si el registro es exitoso
                Toast.makeText(this, "Usuario registrado con éxito", Toast.LENGTH_LONG).show()

                // Puedes redirigir al usuario a la pantalla de inicio de sesión o al área principal
                finish()  // Finaliza la actividad actual (registro)
            } else {
                // Si el registro falla
                Toast.makeText(this, "Error al registrar usuario", Toast.LENGTH_LONG).show()
            }
        }
    }
}
