package com.example.wsrfood.server

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.OPTIONS

interface RetApi {
    @OPTIONS("user")
    fun login(@Header("email") email: String, @Header("password") password: String, @Header("uuid") uuid: String, ): Call<Login>
}