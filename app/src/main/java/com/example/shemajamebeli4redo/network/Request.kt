package com.example.shemajamebeli4redo.network


import com.example.shemajamebeli4redo.models.Match
import retrofit2.Response
import retrofit2.http.GET

interface Request {

    @GET("v3/48bb936e-261a-4471-a362-3bbb3b9a2a58")
    suspend fun getPost(): Response<Match>


}