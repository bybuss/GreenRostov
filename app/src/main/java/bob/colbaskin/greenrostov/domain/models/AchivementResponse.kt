package bob.colbaskin.greenrostov.domain.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * @author bybuss
 */

@Serializable
data class AchivementResponse(
    val id: Int,
    val title: String,
    val description: String,
    @SerialName(value = "file_path")
    val filePath: String,
    val rewards: List<RewardResponse>,
    val goal: Double?,
    val progress: Double?
)