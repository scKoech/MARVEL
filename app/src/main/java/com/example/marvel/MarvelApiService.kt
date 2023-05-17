package com.example.marvel

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import java.security.MessageDigest

interface MarvelApiService {
    @GET("characters")
    fun getCharacters(
        @Query("apikey") apiKey: String,
        @Query("hash") hash: String,
        @Query("ts") timestamp: String
    ): Call<MarvelResponse>
}

data class MarvelResponse(
    val data: MarvelData
)

data class MarvelData(
    val results: List<MarvelCharacter>
)

data class MarvelCharacter(
    val id: Int,
    val name: String,
    val description: String,
    val thumbnail: MarvelThumbnail
)

data class MarvelThumbnail(
    val path: String,
    val extension: String
)

object MarvelApiClient {
    private const val BASE_URL = "https://gateway.marvel.com/v1/public/"
    private const val API_KEY = "df53b47ac4f3376243275b742d18b82a"
    private const val PRIVATE_KEY = "2f010c1d6ea97f284f20217d0cbc1b37008594a1"

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val service = retrofit.create(MarvelApiService::class.java)

    fun getCharacters(): List<MarvelCharacter> {
        val timestamp = System.currentTimeMillis().toString()
        val hash = generateHash(timestamp, PRIVATE_KEY, API_KEY)

        val call = service.getCharacters(API_KEY, hash, timestamp)
        val response = call.execute()

        return response.body()?.data?.results ?: emptyList()
    }

    private fun generateHash(timestamp: String, privateKey: String, publicKey: String): String {
        val input = timestamp + privateKey + publicKey
        val md = MessageDigest.getInstance("MD5")
        val digest = md.digest(input.toByteArray())
        return digest.fold("", { str, it -> str + "%02x".format(it) })
    }
}