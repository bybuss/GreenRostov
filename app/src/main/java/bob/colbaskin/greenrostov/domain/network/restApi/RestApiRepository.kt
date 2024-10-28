package bob.colbaskin.greenrostov.domain.network.restApi

import bob.colbaskin.greenrostov.data.models.Achivement
import bob.colbaskin.greenrostov.data.models.InventoryItem
import bob.colbaskin.greenrostov.data.models.TaskData

/**
 * @author bybuss
 */
interface RestApiRepository {
    suspend fun signUp(
        firstName: String,
        lastName: String,
        address: String,
    )

    suspend fun getTasks(): List<TaskData>

    suspend fun getAchievements(): List<Achivement>

    suspend fun getInventoryItems(): List<InventoryItem>
}