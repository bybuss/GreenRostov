package bob.colbaskin.greenrostov.data.models

/**
 * @author bybuss
 */
data class CodeFetchRequestItem (
    val id: String,
    val time: String,
    val items: List<Item>,
    val totalPrice: Int
)
