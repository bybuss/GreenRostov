package bob.colbaskin.greenrostov.data.network

import androidx.compose.ui.graphics.Color
import bob.colbaskin.greenrostov.data.models.Achivement
import bob.colbaskin.greenrostov.data.models.InventoryItem
import bob.colbaskin.greenrostov.data.models.Reward
import bob.colbaskin.greenrostov.data.models.TaskData
import bob.colbaskin.greenrostov.data.toData
import bob.colbaskin.greenrostov.data.toDomain
import bob.colbaskin.greenrostov.domain.models.AchivementResponse
import bob.colbaskin.greenrostov.domain.models.RewardResponse
import bob.colbaskin.greenrostov.domain.models.SignUpRequest
import bob.colbaskin.greenrostov.domain.network.restApi.RestApiRepository
import bob.colbaskin.greenrostov.domain.network.restApi.RestApiService
import bob.colbaskin.greenrostov.ui.screens.profile.ProfileTab
import javax.inject.Inject

/**
 * @author bybuss
 */
class RestApiRepositoryImpl @Inject constructor(private val restApiService: RestApiService): RestApiRepository {
    override suspend fun signUp(firstName: String, lastName: String, address: String) {
        val request = SignUpRequest(firstName, lastName, address)
        restApiService.signUp(request)
    }

    override suspend fun getTasks(): List<TaskData> {
        return listOf(
            TaskData(
                id = 1,
                title = "Сдать батарейки",
                description = "Ваша задача заключается в том, чтобы отнести определённое количество батареек на специальный пункт.",
                colorStart = Color(0xFFE8B900),
                colorEnd = Color.Black,
                xp = 30,
                reward = 10
            ),
            TaskData(
                id = 2,
                title = "Сортировка мусора",
                description = "Ваша задача заключается в том, чтобы на специальном пункте отсортировать свой мусор.",
                colorStart = Color(0xFF6014EB),
                colorEnd = Color(0xFF822A74),
                xp = 60,
                reward = 15
            ),
            TaskData(
                id = 3,
                title = "Сдать бутылки",
                description = "Ваша задача заключается в том, чтобы сдать пластиковые бутылки на специальном пункте.",
                colorStart = Color(0xFFD25458),
                colorEnd = Color(0xFF343E5B),
                xp = 45,
                reward = 12
            ),
            TaskData(
                id = 1,
                title = "Сдать батарейки",
                description = "Ваша задача заключается в том, чтобы отнести определённое количество батареек на специальный пункт.",
                colorStart = Color(0xFFE8B900),
                colorEnd = Color.Black,
                xp = 30,
                reward = 10
            ),
            TaskData(
                id = 2,
                title = "Сортировка мусора",
                description = "Ваша задача заключается в том, чтобы на специальном пункте отсортировать свой мусор.",
                colorStart = Color(0xFF6014EB),
                colorEnd = Color(0xFF822A74),
                xp = 60,
                reward = 15
            ),
            TaskData(
                id = 3,
                title = "Сдать бутылки",
                description = "Ваша задача заключается в том, чтобы сдать пластиковые бутылки на специальном пункте.",
                colorStart = Color(0xFFD25458),
                colorEnd = Color(0xFF343E5B),
                xp = 45,
                reward = 12
            )
        )
    }

    override suspend fun getAchievements(): List<Achivement> {
        val response = restApiService.getAchievements()
        return response.map { it.toData() }
    }

    override suspend fun getInventoryItems(): List<InventoryItem> {
        val response = restApiService.getInventoryItems()
        return response.map { it.toData() }
    }
}