package mx.com.liverpool.shopapp.products.data.network.dto

data class Refinement(
    val colorHex: String,
    val count: Int,
    val high: String,
    val label: String,
    val low: String,
    val refinementId: String,
    val searchName: String,
    val selected: Boolean,
    val type: String
)