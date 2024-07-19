package mx.com.liverpool.shopapp.products.data.network.dto

data class Navigation(
    val ancester: List<Ancester>,
    val childs: List<Any>,
    val current: List<Current>
)