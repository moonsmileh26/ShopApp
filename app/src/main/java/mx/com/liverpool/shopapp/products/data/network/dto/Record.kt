package mx.com.liverpool.shopapp.products.data.network.dto

import mx.com.liverpool.shopapp.products.domain.model.Product

data class Record(
    val brand: String,
    val category: String,
    val dwPromotionInfo: DwPromotionInfo,
    val groupType: String,
    val isExpressFavoriteStore: Boolean,
    val isExpressNearByStore: Boolean,
    val isHybrid: Boolean,
    val isImportationProduct: Boolean,
    val isMarketPlace: Boolean,
    val lgImage: String,
    val listPrice: Double,
    val maximumListPrice: Double,
    val maximumPromoPrice: Double,
    val minimumListPrice: Double,
    val minimumPromoPrice: Double,
    val plpFlags: List<PlpFlag>,
    val productAvgRating: Double,
    val productDisplayName: String,
    val productId: String,
    val productRatingCount: Int,
    val productType: String,
    val promoPrice: Double,
    val promotionalGiftMessage: String,
    val seller: String,
    val skuRepositoryId: String,
    val smImage: String,
    val variantsColor: List<VariantsColor>,
    val xlImage: String
) {
    fun toDomain(): Product {
        return Product(
            smImage,
            productDisplayName,
            listPrice,
            promoPrice,
            variantsColor.map {
                it.colorHex
            })
    }
}