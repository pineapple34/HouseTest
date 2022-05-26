package com.example.wsrfood.server

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object MyRetrofit {
    fun getRetrofit(): RetApi = Retrofit.Builder()
        .baseUrl("http://smarthome.madskill.ru/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(RetApi::class.java)
}