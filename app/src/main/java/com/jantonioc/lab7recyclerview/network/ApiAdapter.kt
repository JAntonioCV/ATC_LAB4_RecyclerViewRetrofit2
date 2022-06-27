package com.jantonioc.lab7recyclerview.network

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiAdapter {

    val apiClient: ApiClient = Retrofit.Builder()
        .baseUrl("https://dog.ceo")
        .client(OkHttpClient())
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(ApiClient::class.java)

}