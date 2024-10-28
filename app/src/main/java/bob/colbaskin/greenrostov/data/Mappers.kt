package bob.colbaskin.greenrostov.data

import bob.colbaskin.greenrostov.data.models.Achivement
import bob.colbaskin.greenrostov.data.models.InventoryItem
import bob.colbaskin.greenrostov.data.models.Reward
import bob.colbaskin.greenrostov.domain.models.AchivementResponse
import bob.colbaskin.greenrostov.domain.models.RewardResponse
import bob.colbaskin.greenrostov.domain.models.InventoryItemResponse

/**
 * @author bybuss
 */

fun AchivementResponse.toData(): Achivement {
    return Achivement(
        id = this.id,
        title = this.title,
        description = this.description,
        filePath = this.filePath,
        rewards = this.rewards.map { it.toData() },
        goal = this.goal,
        progress = this.progress
    )
}

fun Achivement.toDomain(): AchivementResponse {
    return AchivementResponse(
        id = this.id,
        title = this.title,
        description = this.description,
        filePath = this.filePath,
        rewards = this.rewards.map { it.toDomain() },
        goal = this.goal,
        progress = this.progress
    )
}

fun RewardResponse.toData(): Reward {
    return Reward(
        id = this.id,
        title = this.title,
        filePath = this.filePath ?: "",
        amount = this.amount
    )
}

fun Reward.toDomain(): RewardResponse {
    return RewardResponse(
        id = this.id,
        title = this.title,
        filePath = this.filePath,
        amount = this.amount
    )
}

fun InventoryItemResponse.toData(): InventoryItem {
    return InventoryItem(
        id = this.id,
        title = this.title,
        filePath = this.filePath,
        amount = this.amount
    )
}

fun InventoryItem.toDomain(): InventoryItemResponse {
    return InventoryItemResponse(
        id = this.id,
        title = this.title,
        filePath = this.filePath,
        amount = this.amount
    )
}