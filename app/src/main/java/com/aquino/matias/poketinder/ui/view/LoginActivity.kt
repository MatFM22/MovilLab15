package com.aquino.matias.poketinder.ui.view

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.aquino.matias.poketinder.data.database.SharedPreferencesRepository
import com.aquino.matias.poketinder.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private lateinit var sharedPreferencesRepository: SharedPreferencesRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        sharedPreferencesRepository = SharedPreferencesRepository().apply {
            setSharedPreference(this@LoginActivity)
        }

        binding.btnLogin.setOnClickListener {
            val email = binding.edtEmail.text.toString()
            val password = binding.edtPassword.text.toString()
            validateLogin(email, password)
        }

        binding.btnRegister.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
            finish()
        }
    }
    /*
    private fun validateLogin(email: String, password: String) {
        val savedEmail = sharedPreferencesRepository.getUserEmail()
        val savedPassword = sharedPreferencesRepository.getUserPassword()

        if (email.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Ingrese los datos completos", Toast.LENGTH_SHORT).show()
            return
        }

        if (email == savedEmail && password == savedPassword) {
            // Login exitoso
            Toast.makeText(this, "Inicio de sesión exitoso", Toast.LENGTH_SHORT).show()
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        } else {
            Toast.makeText(this, "Error: usuario o contraseña incorrectos", Toast.LENGTH_SHORT).show()
        }
    }
    */
    private fun validateLogin(email: String, password: String) {
        val savedEmail = sharedPreferencesRepository.getUserEmail()
        val savedPassword = sharedPreferencesRepository.getUserPassword()

        if (email.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Ingrese los datos completos", Toast.LENGTH_SHORT).show()
            return
        }

        if (email == savedEmail && password == savedPassword) {
            // Login exitoso
            Toast.makeText(this, "Inicio de sesión exitoso", Toast.LENGTH_SHORT).show()
            startActivity(Intent(this, SplashActivity::class.java))
            finish()
        } else {
            Toast.makeText(this, "Error: usuario o contraseña incorrectos", Toast.LENGTH_SHORT).show()
        }
    }
}
