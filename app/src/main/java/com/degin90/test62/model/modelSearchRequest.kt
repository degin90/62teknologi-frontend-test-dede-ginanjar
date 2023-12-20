package com.degin90.test62.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable


class modelSearchRequest : Serializable {
    var location: String? = null
    var latitude: Float? = null
    var longitude: Float? = null
    var term: String? = null
    var radius: Int? = null
    var categories: Float? = null
    var locale: String? = null
    var price: Int? = null
    var open_now: Boolean? = null
    var open_at: Int? = null
    var sort_by: Int? = null
    var device_platform: String? = null
    var reservation_date: String? = null
    var reservation_time: String? = null
    var reservation_covers: Int? = null
    var matches_party_size_param: Boolean? = null
    var limit: Int? = null
}

class modelSearchRespond {
    @SerializedName("businesses")
    @Expose
    var businesses: List<Business>? = null

    @SerializedName("region")
    @Expose
    var region: Region? = null

    @SerializedName("total")
    @Expose
    var total: Int? = null
}

class Business {
    @SerializedName("alias")
    @Expose
    var alias: String? = null

    @SerializedName("categories")
    @Expose
    var categories: List<Category>? = null

    @SerializedName("coordinates")
    @Expose
    var coordinates: Coordinates? = null

    @SerializedName("display_phone")
    @Expose
    var displayPhone: String? = null

    @SerializedName("distance")
    @Expose
    var distance: Double? = null

    @SerializedName("id")
    @Expose
    var id: String? = null

    @SerializedName("image_url")
    @Expose
    var imageUrl: String? = null

    @SerializedName("is_closed")
    @Expose
    var isClosed: Boolean? = null

    @SerializedName("location")
    @Expose
    var location: Location? = null

    @SerializedName("name")
    @Expose
    var name: String? = null

    @SerializedName("phone")
    @Expose
    var phone: String? = null

    @SerializedName("price")
    @Expose
    var price: String? = null

    @SerializedName("rating")
    @Expose
    var rating: Float? = null

    @SerializedName("review_count")
    @Expose
    var reviewCount: Int? = null

    @SerializedName("transactions")
    @Expose
    var transactions: List<String>? = null

    @SerializedName("url")
    @Expose
    var url: String? = null
}

class Category {
    @SerializedName("alias")
    @Expose
    var alias: String? = null

    @SerializedName("title")
    @Expose
    var title: String? = null
}

class Center {
    @SerializedName("latitude")
    @Expose
    var latitude: Double? = null

    @SerializedName("longitude")
    @Expose
    var longitude: Double? = null
}

class Coordinates {
    @SerializedName("latitude")
    @Expose
    var latitude: Double? = null

    @SerializedName("longitude")
    @Expose
    var longitude: Double? = null
}

class Location {
    @SerializedName("address1")
    @Expose
    var address1: String? = null

    @SerializedName("address2")
    @Expose
    var address2: String? = null

    @SerializedName("address3")
    @Expose
    var address3: String? = null

    @SerializedName("city")
    @Expose
    var city: String? = null

    @SerializedName("country")
    @Expose
    var country: String? = null

    @SerializedName("display_address")
    @Expose
    var displayAddress: List<String>? = null

    @SerializedName("state")
    @Expose
    var state: String? = null

    @SerializedName("zip_code")
    @Expose
    var zipCode: String? = null
}

class Region {
    @SerializedName("center")
    @Expose
    var center: Center? = null
}