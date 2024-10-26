package bob.colbaskin.greenrostov.domain.network.restApi

/**
 * @author bybuss
 */
interface RestApiRepository {
    suspend fun signUp(
        firstName: String,
        lastName: String,
        address: String,
    )
}