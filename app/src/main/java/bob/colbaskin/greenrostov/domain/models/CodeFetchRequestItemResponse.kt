package bob.colbaskin.greenrostov.domain.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * @author bybuss
 */

@Serializable
data class CodeFetchRequestItemResponse(
    val id: String,
    val time: String,
    val item: List<ItemResponse>,
    @SerialName(value = "total_price")
    val totalPrice: Int
)