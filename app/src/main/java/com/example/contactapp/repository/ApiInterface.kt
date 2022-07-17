package com.example.contactapp.repository

import com.example.contactapp.model.ResponseUsers
import retrofit2.Response
import retrofit2.http.GET

interface ApiInterface {
    @GET("api/users?") suspend fun getAllUsers(): Response<ResponseUsers>
}