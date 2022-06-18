package com.example.toogoodtogo

data class Pack(
    val id: Int,
    val name: String,
    val price: Double,
    val desc: String,
    val taken: Boolean,
    val supplier: String,
    val date_start: Int,
    val date_end: Int
) {

}