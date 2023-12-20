package com.degin90.test62.api

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object ApiNetwork {
    private var retrofit: Retrofit? = null
    fun getClient(baseUrl: String?): Retrofit? {
        if (retrofit == null) {
            val httpClient = OkHttpClient.Builder()
                .addInterceptor(HttpLoggingInterceptor().apply {
                    level = HttpLoggingInterceptor.Level.BODY
                })
                .build()
            val gson = GsonBuilder()
                .serializeNulls()
                .create()

            retrofit = Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(httpClient)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()
        }
        return retrofit
    }
}
object ApiUtils {
    val ApiServices: ApiInterfaces?
        get() = ApiNetwork.getClient("https://api.yelp.com/v3/")?.create(ApiInterfaces::class.java)
}