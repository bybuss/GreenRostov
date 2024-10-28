package bob.colbaskin.greenrostov.domain.network.restApi

import bob.colbaskin.greenrostov.data.models.TaskData
import bob.colbaskin.greenrostov.domain.models.AchivementResponse
import bob.colbaskin.greenrostov.domain.models.InventoryItemResponse
import bob.colbaskin.greenrostov.domain.models.SignUpRequest
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

/**
 * @author bybuss
 */
interface RestApiService {
    @POST("user")
    suspend fun signUp(@Body request: SignUpRequest)

//    @POST("purchasers/confirm_code_and_fetch_receipts")
//    suspend fun confirmAndFetchReceipts(@Body request: SignUpRequest)

    suspend fun getTasks(): List<TaskData>

    @GET("achievements")
    suspend fun getAchievements(): List<AchivementResponse>

    @GET("inventory")
    suspend fun getInventoryItems(): List<InventoryItemResponse>

}