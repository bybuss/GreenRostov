package bob.colbaskin.greenrostov.domain.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * @author bybuss
 */

@Serializable
data class RewardResponse (
    val id: Int,
    val title: String,
    @SerialName(value = "file_path")
    val filePath: String?,
    val amount: Int
)