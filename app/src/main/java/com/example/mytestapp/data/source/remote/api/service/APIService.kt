package com.example.mytestapp.data.source.remote.api.service




import com.example.mytestapp.data.source.remote.api.response.AnimeResponse
import retrofit2.Response
import retrofit2.http.GET

interface APIService {
    @GET("anime")
    suspend fun getAnime(): Response<AnimeResponse>

}