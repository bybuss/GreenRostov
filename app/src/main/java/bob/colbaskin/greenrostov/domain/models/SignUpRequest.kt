package bob.colbaskin.greenrostov.domain.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * @author bybuss
 */
@Serializable
data class SignUpRequest (
    @SerialName(value = "first_name")
    val firstName: String,
    @SerialName(value = "last_name")
    val lastName: String,
    val address: String
)
