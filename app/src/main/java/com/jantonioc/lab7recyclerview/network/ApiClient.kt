package com.jantonioc.lab7recyclerview.network

import com.jantonioc.lab7recyclerview.dto.DogResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiClient {

    @GET("/api/breed/{param}/images")
    suspend fun getListOfPet(@Path("param")param: String): Response<DogResponse>



}