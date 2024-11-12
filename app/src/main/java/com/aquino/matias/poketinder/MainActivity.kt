package com.aquino.matias.poketinder

import android.os.Bundle
import android.os.PersistableBundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.aquino.matias.poketinder.PokemonResponse
import com.aquino.matias.poketinder.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

import android.widget.Toast
import androidx.core.view.isVisible


class MainActivity : AppCompatActivity() {
    /*
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
    */
    /*
    private var listPokemon: List<PokemonResponse> = emptyList()

    private val adapter by lazy { PokemonAdapter(listPokemon) }

    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://pokeapi.co")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    */

    /*
    private var listPokemon: List<PokemonResponse> = emptyList()

    private val adapter by lazy { PokemonAdapter(listPokemon) }

    private lateinit var binding : ActivityMainBinding

    private val viewModel by lazy { MainViewModel() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.rvTinderPokemon.adapter = adapter
        getAllPokemons()
    }

    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://pokeapi.co")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    private fun getAllPokemons() {
        CoroutineScope(Dispatchers.IO).launch {
            val request = getRetrofit().create(PokemonApi::class.java).getPokemon()
            if (request.isSuccessful) {
                request.body()?.let {
                    runOnUiThread {
                        adapter.list = it.results
                        adapter.notifyDataSetChanged()
                    }
                }
            }
        }
    }
    */

    private var listPokemon: List<PokemonResponse> = emptyList()

    private val adapter by lazy { PokemonAdapter(listPokemon) }

    private lateinit var binding : ActivityMainBinding

    private val viewModel by lazy { MainViewModel() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.rvTinderPokemon.adapter = adapter
        observeValues()
    }

    private fun observeValues() {
        viewModel.isLoading.observe(this) { isLoading ->
            binding.progressBar.isVisible = isLoading
        }

        viewModel.pokemonList.observe(this) { pokemonList ->
            adapter.list = pokemonList
            adapter.notifyDataSetChanged()
        }

        viewModel.errorApi.observe(this) { errorMessage ->
            showMessage(errorMessage)
        }
    }

    private fun showMessage(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

}