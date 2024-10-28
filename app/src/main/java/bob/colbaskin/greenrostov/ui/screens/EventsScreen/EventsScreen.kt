package bob.colbaskin.greenrostov.ui.screens.EventsScreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.DonutLarge
import androidx.compose.material.icons.filled.SwapHorizontalCircle
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.tooling.preview.Preview
import bob.colbaskin.greenrostov.R
import bob.colbaskin.greenrostov.data.models.TaskData
import bob.colbaskin.greenrostov.data.models.taskList

/**
 * @author bybuss
 */
@Composable
fun EventsScreen(
    tasks: List<TaskData>,
    currentXp: Int,
    maxXp: Int,
    currentReward: String,
    level: String
) {
    Scaffold(
        topBar = { TopAppBar(currentXp = currentXp, maxXp = maxXp, currentReward = currentReward, level = level) }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .padding(horizontal = 16.dp)
        ) {
            Spacer(modifier = Modifier.height(8.dp))
            tasks.forEach { task ->
                TaskCard(
                    taskData = task,
                    modifier = Modifier
                        .fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(16.dp))
            }
        }
    }
}

@Composable
fun TopAppBar(currentXp: Int, maxXp: Int, currentReward: String, level: String) {
    val progress = (currentXp.toFloat() / maxXp.toFloat()).coerceIn(0f, 1f)

    Column(
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.End,
        modifier = Modifier.padding(16.dp)
    ) {
        Box(
            modifier = Modifier
                .background(Color(0xFFFFD700), RoundedCornerShape(8.dp))
                .padding(8.dp),
        ) {
            Text(
                text = currentReward,
                color = Color.Black,
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.Black, RoundedCornerShape(12.dp))
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Row {
                    Text(
                        text = "Осенний Сезон 🍁",
                        color = Color.White,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.weight(1f))
                    Text(
                        text = level,
                        color = Color(0xFFFFD700),
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
                Spacer(modifier = Modifier.height(8.dp))
                LinearProgressIndicator(
                    progress = { progress },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(6.dp),
                    color = Color.Green,
                )
                Row {
                    Text(
                        text = "$currentXp xp",
                        color = Color.White,
                        fontSize = 12.sp
                    )
                    Spacer(modifier = Modifier.weight(1f))
                    Text(
                        text = "$maxXp xp",
                        color = Color.White,
                        fontSize = 12.sp
                    )
                }
            }
        }
    }
}

@Composable
fun TaskCard(
    taskData: TaskData,
    modifier: Modifier = Modifier
) {
    var showDialog by remember { mutableStateOf(false) }
    var showQrCode by remember { mutableStateOf(false) }

    Row(
        modifier = modifier
            .background(
                brush = Brush.horizontalGradient(listOf(taskData.colorStart, taskData.colorEnd)),
                shape = RoundedCornerShape(16.dp)
            )
            .padding(16.dp)
    ) {
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = taskData.title,
                color = Color.White,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = taskData.description,
                color = Color.White.copy(alpha = 0.8f),
                fontSize = 12.sp
            )
        }
        Column(horizontalAlignment = Alignment.End) {
            Text(
                text = "${taskData.reward} \uD83C\uDF41",
                color = Color.Yellow,
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.End
            )
            Text(
                text = "${taskData.xp} xp",
                color = Color.Yellow,
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.End
            )
            Spacer(modifier = Modifier.height(8.dp))
            IconButton(onClick = { showDialog = true }) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                    contentDescription = "Go",
                    tint = Color.White,
                    modifier = Modifier.size(24.dp)
                )
            }
        }
    }

    if (showDialog) {
        AlertDialog(
            onDismissRequest = { showDialog = false; showQrCode = false },
            confirmButton = {
                Button(onClick = { showQrCode = !showQrCode }) {
                    Text(if (showQrCode) "Скрыть QR-код" else "Показать QR-код")
                }
            },
            dismissButton = {
                Button(onClick = { showDialog = false; showQrCode = false }) {
                    Text("Закрыть")
                }
            },
            title = {
                Text(
                    text = taskData.title,
                    color = Color.White,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
            },
            text = {
                Column(
                    modifier = Modifier.padding(16.dp)
                ) {
                    Text(
                        text = taskData.description,
                        color = Color.White.copy(alpha = 0.8f),
                        fontSize = 14.sp
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Row {
                        Text(text = "Награда: ${taskData.reward} \uD83C\uDF41", color = Color.Yellow, fontSize = 14.sp)
                        Spacer(modifier = Modifier.width(16.dp))
                        Text(text = "Опыт: ${taskData.xp} xp", color = Color.Yellow, fontSize = 14.sp)
                        Spacer(modifier = Modifier.width(16.dp))
                        IconButton(onClick = {  }) {
                            Icon(
                                imageVector = Icons.Default.SwapHorizontalCircle,
                                contentDescription = "Go",
                                tint = Color.White,
                                modifier = Modifier.size(24.dp)
                            )
                        }
                    }
                    if (showQrCode) {
                        Spacer(modifier = Modifier.height(4.dp))
                        Image(
                            painter = painterResource(id = R.drawable.moc_avatar),
                            contentDescription = "QR-код",
                            modifier = Modifier
                                .size(350.dp)
                                .align(Alignment.CenterHorizontally)
                        )
                    }
                }
            },
            containerColor = Color.Transparent,
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    brush = Brush.horizontalGradient(
                        listOf(
                            taskData.colorStart,
                            taskData.colorEnd
                        )
                    ),
                    shape = RoundedCornerShape(16.dp)
                )
        )
    }
}

@Composable
fun PreviewEventsScreen() {
    EventsScreen(
        tasks = taskList,
        currentXp = 250,
        maxXp = 300,
        currentReward = "10 🍁",
        level = "Level - 1"
    )
}