package bob.colbaskin.greenrostov.domain.models

import kotlinx.serialization.Serializable

/**
 * @author bybuss
 */

@Serializable
data class ItemResponse(
    val name: String,
    val count: Int,
    val price: Int
)