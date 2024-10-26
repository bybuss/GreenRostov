package bob.colbaskin.greenrostov.data.models

import androidx.compose.ui.graphics.Color

/**
 * @author bybuss
 */
data class TaskData(
    val id: Int,
    val title: String,
    val description: String,
    val colorStart: Color,
    val colorEnd: Color,
    val xp: Int,
    val reward: Int
)

val taskList = listOf(
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
