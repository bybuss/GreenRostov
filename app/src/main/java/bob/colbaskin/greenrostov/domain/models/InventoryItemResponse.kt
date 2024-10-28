package bob.colbaskin.greenrostov.domain.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class InventoryItemResponse (
    val id: Int,
    val title: String,
    @SerialName("file_path")
    val filePath: String,
    val amount: Int
)