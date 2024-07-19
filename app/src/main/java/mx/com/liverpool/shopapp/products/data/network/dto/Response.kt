package mx.com.liverpool.shopapp.products.data.network.dto

data class Response(
    val pageType: String,
    val plpResults: PlpResults,
    val status: Status
)