package mx.com.liverpool.shopapp.products.domain.model

data class Product(
    val image: String,
    val name: String,
    val listPrice: Double,
    val promoPrice: Double,
    val colors: List<String>
)
