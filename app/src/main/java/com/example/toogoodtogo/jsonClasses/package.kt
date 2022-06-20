package com.example.toogoodtogo.jsonClasses

import com.google.gson.annotations.SerializedName


data class Package (

    @SerializedName("id"         ) var id        : String? = null,
    @SerializedName("name"       ) var name      : String? = null,
    @SerializedName("price"      ) var price     : String? = null,
    @SerializedName("desc"       ) var desc      : String? = null,
    @SerializedName("taken"      ) var taken     : String? = null,
    @SerializedName("supplier"   ) var supplier  : String? = null,
    @SerializedName("date_start" ) var dateStart : String? = null,
    @SerializedName("date_end"   ) var dateEnd   : String? = null

)