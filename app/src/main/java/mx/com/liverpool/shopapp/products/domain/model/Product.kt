package mx.com.liverpool.shopapp.products.domain.model

data class Product(
    val image: String,
    val name: String,
    var listPrice: String,
    val promoPrice: String,
    val colors: List<String>
) {
    fun toProduct(): Product {
        return Product(
            image,
            name,
            listPrice,
            promoPrice,
            colors
        )
    }
}

