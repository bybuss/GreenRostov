package bob.colbaskin.greenrostov.domain.local

import kotlinx.coroutines.flow.Flow

/**
 * @author bybuss
 */
interface DataStoreRepository {

    suspend fun saveOnBoardingState(completed: Boolean)

    fun readOnBoardingState(): Flow<Boolean>

    suspend fun saveToken(token: String)

    fun getToken(): Flow<String>

//    suspend fun clearToken()
}