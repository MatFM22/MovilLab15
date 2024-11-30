package com.aquino.matias.poketinder.data.network

import com.aquino.matias.poketinder.data.model.PokemonListResponse
import retrofit2.Response
import retrofit2.http.GET

interface PokemonApi {
    @GET("/api/v2/pokemon")
    suspend fun getPokemon(): Response<PokemonListResponse>
}