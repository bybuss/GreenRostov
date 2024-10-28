package bob.colbaskin.greenrostov.data.models

import bob.colbaskin.greenrostov.domain.models.RewardResponse

/**
 * @author bybuss
 */
data class Achivement (
    val id: Int,
    val title: String,
    val description: String,
    val filePath: String,
    val rewards: List<Reward>,
    val goal: Double?,
    val progress: Double?
)
