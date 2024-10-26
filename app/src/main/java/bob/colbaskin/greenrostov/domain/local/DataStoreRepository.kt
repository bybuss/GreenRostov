package bob.colbaskin.greenrostov.domain.local

import kotlinx.coroutines.flow.Flow

/**
 * @author bybuss
 */
interface DataStoreRepository {

    suspend fun saveOnBoardingState(completed: Boolean)

    fun readOnBoardingState(): Flow<Boolean>
}