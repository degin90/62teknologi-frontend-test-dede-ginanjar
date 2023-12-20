package com.degin90.test62.model

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName


class reviewResponds {
    @SerializedName("possible_languages")
    @Expose
    var possibleLanguages: List<String>? = null

    @SerializedName("reviews")
    @Expose
    var reviews: List<Review>? = null

    @SerializedName("total")
    @Expose
    var total: Int? = null
}

class Review {
    @SerializedName("id")
    @Expose
    var id: String? = null

    @SerializedName("url")
    @Expose
    var url: String? = null

    @SerializedName("text")
    @Expose
    var text: String? = null

    @SerializedName("rating")
    @Expose
    var rating: Int? = null

    @SerializedName("time_created")
    @Expose
    var timeCreated: String? = null

    @SerializedName("user")
    @Expose
    var user: User? = null
}

class User {
    @SerializedName("id")
    @Expose
    var id: String? = null

    @SerializedName("profile_url")
    @Expose
    var profileUrl: String? = null

    @SerializedName("image_url")
    @Expose
    var imageUrl: String? = null

    @SerializedName("name")
    @Expose
    var name: String? = null
}