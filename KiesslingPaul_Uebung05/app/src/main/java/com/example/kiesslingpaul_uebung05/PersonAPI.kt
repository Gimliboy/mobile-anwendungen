package com.example.kiesslingpaul_uebung05

import retrofit2.Call
import retrofit2.http.GET

interface PersonAPI {
    @GET("")
    fun getPerson(): Call<Person>
}