package mx.com.liverpool.shopapp.products.data.network.dto

data class PlpState(
    val categoryId: String,
    val currentFilters: String,
    val currentSortOption: String,
    val firstRecNum: Int,
    val lastRecNum: Int,
    val originalSearchTerm: String,
    val plpSellerName: String,
    val recsPerPage: Int,
    val totalNumRecs: Int
)