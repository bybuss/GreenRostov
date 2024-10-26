package bob.colbaskin.greenrostov.data.network

import bob.colbaskin.greenrostov.domain.models.SignUpRequest
import bob.colbaskin.greenrostov.domain.network.restApi.RestApiRepository
import bob.colbaskin.greenrostov.domain.network.restApi.RestApiService
import javax.inject.Inject

/**
 * @author bybuss
 */
class RestApiRepositoryImpl @Inject constructor(private val restApiService: RestApiService): RestApiRepository {
    override suspend fun signUp(firstName: String, lastName: String, address: String) {
        val request = SignUpRequest(firstName, lastName, address)
        restApiService.signUp(request)
    }
}