package bob.colbaskin.greenrostov.ui.screens.EventsScreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Warning
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

/**
 * @author bybuss
 */

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EventsScreen() {
    Scaffold(
        topBar = { TopAppBar() }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .padding(horizontal = 16.dp)
        ) {
            Spacer(modifier = Modifier.height(8.dp))
            TaskCard(
                title = "Сдать батарейки",
                description = "Ваша задача заключается в том, чтобы отнести определённое количество батареек на специальный пункт.",
                colorStart = Color(0xFFE8B900),
                colorEnd = Color.Black,
                xp = "30 xp",
                reward = "10 🍁",
                modifier = Modifier
                    .fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(16.dp))
            TaskCard(
                title = "Сортировка мусора",
                description = "Ваша задача заключается в том, чтобы на специальном пункте отсортировать свой мусор.",
                colorStart = Color(0xFF6014EB),
                colorEnd = Color(0xFF822A74),
                xp = "60 xp",
                reward = "15 🍁",
                modifier = Modifier
                    .fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(16.dp))
            TaskCard(
                title = "Сдать бутылки",
                description = "Ваша задача заключается в том, чтобы сдать пластиковые бутылки на специальном пункте.",
                colorStart = Color(0xFFD25458),
                colorEnd = Color(0xFF343E5B),
                xp = "45 xp",
                reward = "12 🍁",
                modifier = Modifier
                    .fillMaxWidth()
            )
        }
    }
}

@Composable
fun TopAppBar() {
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
                text = "10 🍁",
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
                Row() {
                    Text(
                        text = "Осенний Сезон 🍁",
                        color = Color.White,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.weight(1f))
                    Text(
                        text = "Level - 1",
                        color = Color(0xFFFFD700),
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
                Spacer(modifier = Modifier.height(8.dp))
                LinearProgressIndicator(
                    progress = { 0.50f },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(6.dp),
                    color = Color.Green,
                )
                Row() {
                    Text(
                        text = "50 xp",
                        color = Color.White,
                        fontSize = 12.sp
                    )
                    Spacer(modifier = Modifier.weight(1f))
                    Text(
                        text = "300 xp",
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
    title: String,
    description: String,
    colorStart: Color,
    colorEnd: Color,
    xp: String,
    reward: String,
    modifier: Modifier = Modifier
) {
    var showDialog by remember { mutableStateOf(false) }
    var showQrCode by remember { mutableStateOf(false) }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                brush = Brush.horizontalGradient(listOf(colorStart, colorEnd)),
                shape = RoundedCornerShape(16.dp)
            )
            .padding(16.dp)
    ) {
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = title,
                color = Color.White,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = description,
                color = Color.White.copy(alpha = 0.8f),
                fontSize = 12.sp
            )
        }
        Column(horizontalAlignment = Alignment.End) {
            Text(
                text = reward,
                color = Color.Yellow,
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.End
            )
            Text(
                text = xp,
                color = Color.Yellow,
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.End
            )
            Spacer(modifier = Modifier.height(8.dp))
            IconButton(onClick = { showDialog = true }) {
                Icon(
                    imageVector = Icons.Default.ArrowForward,
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
                    text = title,
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
                        text = description,
                        color = Color.White.copy(alpha = 0.8f),
                        fontSize = 14.sp
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Row {
                        Text(text = "Награда: $reward", color = Color.Yellow, fontSize = 14.sp)
                        Spacer(modifier = Modifier.width(16.dp))
                        Text(text = "Опыт: $xp", color = Color.Yellow, fontSize = 14.sp)
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
                    brush = Brush.horizontalGradient(listOf(colorStart, colorEnd)),
                    shape = RoundedCornerShape(16.dp)
                )
        )
    }
}


@Preview(showBackground = true)
@Composable
fun PreviewEventsScreen() {
    EventsScreen()
}