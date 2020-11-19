package com.example.kiesslingpaul_uebung05

import retrofit2.Call
import retrofit2.http.GET
import java.sql.ResultSet

interface PersonAPI {
    @GET("?format=json")
    fun getPerson(): Call<Results>

    @GET("?format=json&gender=male")
    fun getMalePerson(): Call<Results>
}