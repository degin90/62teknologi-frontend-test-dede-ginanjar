package com.degin90.test62.api


import com.degin90.test62.model.Business
import com.degin90.test62.model.modelSearchRespond
import com.degin90.test62.model.reviewResponds
import retrofit2.Call
import retrofit2.http.*


interface ApiInterfaces {

    @GET("businesses/search")
    fun businessesSearch(
        @Header("Authorization") authorization: String,
        @Query("location") location: String,
        @Query("term") term: String
    ): Call<modelSearchRespond>

    @GET("businesses/{alias}/reviews")
    fun getReview(
        @Header("Authorization") authorization: String,
        @Path("alias") alias: String,
        @Query("limit") limit: Int,
        @Query("sort_by") sort: String
    ): Call<reviewResponds>

    @GET("businesses/{alias}")
    fun getBusines(
        @Header("Authorization") authorization: String,
        @Path("alias") alias: String
    ): Call<Business>

}
