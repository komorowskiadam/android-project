package com.example.toogoodtogo

import com.google.gson.annotations.SerializedName


data class Supplier (

    @SerializedName("id"        ) var id        : String? = null,
    @SerializedName("name"      ) var name      : String? = null,
    @SerializedName("latitude"  ) var latitude  : String? = null,
    @SerializedName("longitude" ) var longitude : String? = null

)