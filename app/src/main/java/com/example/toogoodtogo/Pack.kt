package com.example.toogoodtogo

import com.google.gson.annotations.SerializedName

data class Pack(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("price")
    val price: Double,
    @SerializedName("desc")
    val desc: String,
    @SerializedName("taken")
    val taken: Boolean,
    @SerializedName("supplier")
    val supplier: String,
    @SerializedName("date_start")
    val date_start: Int,
    @SerializedName("date_end")
    val date_end: Int
)