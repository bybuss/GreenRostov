package bob.colbaskin.greenrostov.ui.screens.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

/**
 * @author bybuss
 */
enum class ProfileTab { Activity, Inventory, Achievements }

@Composable
fun ProfileScreen(modifier: Modifier = Modifier) {
    var selectedTab by remember { mutableStateOf(ProfileTab.Activity) }

    Column(
        modifier = modifier.fillMaxSize()
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.40f),
            contentAlignment = Alignment.Center
        ) {
            ProfileHeader()
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
        ) {
            Column {
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    ProfileTabButton("Активность", selectedTab == ProfileTab.Activity) { selectedTab = ProfileTab.Activity }
                    ProfileTabButton("Инвентарь", selectedTab == ProfileTab.Inventory) { selectedTab = ProfileTab.Inventory }
                    ProfileTabButton("Достижения", selectedTab == ProfileTab.Achievements) { selectedTab = ProfileTab.Achievements }
                }
                HorizontalDivider(
                    modifier = Modifier.fillMaxWidth(),
                    thickness = 2.dp,
                    color = Color.Black
                )

                Box(modifier = Modifier.fillMaxSize()) {
                    when (selectedTab) {
                        ProfileTab.Activity -> ActivityContent()
                        ProfileTab.Inventory -> InventoryContent()
                        ProfileTab.Achievements -> AchievementsContent()
                    }
                }
            }
        }
    }
}

@Composable
fun ProfileHeader() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Box(
            modifier = Modifier
                .size(80.dp)
                .background(Color.Gray),
            contentAlignment = Alignment.Center
        ) {
            Text(text = "Avatar", color = Color.White)
        }
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = "Иванчик Зольчик", style = MaterialTheme.typography.titleMedium)
    }
}

@Composable
fun ProfileTabButton(title: String, selected: Boolean, onClick: () -> Unit) {
    Text(
        text = title,
        color = if (selected) Color.Black else Color.Gray,
        modifier = Modifier
            .clickable(onClick = onClick)
            .padding(8.dp)
    )
}

@Composable
fun ActivityContent() {
    LazyColumn(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        items(10) { index ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
            ) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.Start
                ) {
                    Text(text = "Выполните 3 задания за 30 дней", style = MaterialTheme.typography.bodyMedium)
                    Text(text = "Статус: Выполнено", style = MaterialTheme.typography.bodySmall)
                }
            }
        }
    }
}

@Composable
fun InventoryContent() {
    LazyColumn(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        items(5) { index ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
            ) {
                Row(
                    modifier = Modifier.padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .size(50.dp)
                            .background(Color.LightGray),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(text = "Image", color = Color.Black)
                    }
                    Spacer(modifier = Modifier.width(16.dp))
                    Text(
                        text = "Купон на ${5 * (index + 1)}% - Скидка на покупки",
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            }
        }
    }
}

@Composable
fun AchievementsContent() {
    LazyVerticalGrid(
        columns = GridCells.Fixed(3),
        modifier = Modifier.fillMaxSize().padding(16.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(15) { index ->
            Card(
                modifier = Modifier.size(100.dp).background(Color.LightGray),
            ) {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier.fillMaxSize()
                ) {
                    Text(text = "Достижение ${index + 1}", color = Color.Black)
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ProfileScreenPreview() {
    ProfileScreen()
}