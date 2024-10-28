package bob.colbaskin.greenrostov.ui.screens.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import bob.colbaskin.greenrostov.R
import bob.colbaskin.greenrostov.data.models.Achivement
import bob.colbaskin.greenrostov.data.models.InventoryItem
import bob.colbaskin.greenrostov.data.models.Reward
import bob.colbaskin.greenrostov.data.models.TaskData
import coil.compose.AsyncImage

/**
 * @author bybuss
 */
enum class ProfileTab { Activity, Inventory, Achievements }

@Composable
fun ProfileScreen(
    modifier: Modifier = Modifier,
    viewModel: ProfileViewModel
) {
    var selectedTab by remember { mutableStateOf(ProfileTab.Activity) }
    val tasks by viewModel.tasks.collectAsState()
    val items by viewModel.inventory.collectAsState()
    val achives by viewModel.achievements.collectAsState()

    Column(
        modifier = modifier.fillMaxSize()
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.35f),
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
                    thickness = 1.dp,
                    color = Color.Black
                )

                Box(modifier = Modifier.fillMaxSize()) {
                    when (selectedTab) {
                        ProfileTab.Activity -> ActivityContent(tasks)
                        ProfileTab.Inventory -> InventoryContent(items)
                        ProfileTab.Achievements -> AchievementsContent(achives)
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
        Image(
            painter = painterResource(id = R.drawable.moc_avatar),
            contentDescription = "Avatar",
            modifier = Modifier
                .size(100.dp)
                .background(
                    color = Color.Gray,
                    shape = RoundedCornerShape(50.dp)
                )
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = "Иванчик Зольчик", fontSize = 20.sp, fontWeight = FontWeight.Bold)
        Text(text = "г. Ростов-на-Дону.", color = Color.Gray, fontSize = 14.sp)
    }
}

@Composable
fun ProfileTabButton(title: String, selected: Boolean, onClick: () -> Unit) {
    Text(
        text = title,
        color = if (selected) Color.Black else Color.Gray,
        fontSize = 16.sp,
        fontWeight = if (selected) FontWeight.Bold else FontWeight.Normal,
        modifier = Modifier
            .clickable(onClick = onClick)
            .padding(8.dp)
    )
}

@Composable
fun ActivityContent(tasks: List<TaskData>) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        items(tasks.size) { index ->
            val task = tasks[index]
            TaskCard(task)
        }
    }
}

@Composable
fun TaskCard(task: TaskData) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Box(
            modifier = Modifier
                .background(
                    brush = Brush.horizontalGradient(
                        colors = listOf(task.colorStart, task.colorEnd)
                    ),
                    shape = RoundedCornerShape(16.dp)
                )
                .padding(16.dp)
        ) {
            Column {
                Text(
                    text = task.title,
                    color = Color.White,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = task.description,
                    color = Color.White,
                    fontSize = 14.sp
                )
                Spacer(modifier = Modifier.height(8.dp))
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = "XP: ${task.xp}",
                        color = Color.White,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = "Награда: ${task.reward}",
                        color = Color.White,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    }
}

@Composable
fun InventoryContent(inventory: List<InventoryItem>) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        items(inventory.size) { index ->
            val item = inventory[index]
            InventoryCard(item)
        }
    }
}

@Composable
fun InventoryCard(item: InventoryItem) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFEFEFEF))
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = R.drawable.moc_avatar),
                contentDescription = item.title,
                modifier = Modifier
                    .size(50.dp)
                    .background(Color.LightGray)
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column {
                Text(
                    text = item.title,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}

@Composable
fun AchievementsContent(achives: List<Achivement>) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(3),
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(achives.size) { index ->
            val item = achives[index]
            RewardCard(item)
        }
    }
}

@Composable
fun RewardCard(achive: Achivement) {
    Card(
        modifier = Modifier
            .wrapContentHeight()
            .padding(8.dp)
            .width(120.dp),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color.Black)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            AsyncImage(
                model = achive.filePath,
                contentDescription = achive.title,
                modifier = Modifier
                    .size(90.dp)
                    .padding(bottom = 8.dp)
            )
            Text(
                text = achive.title,
                color = Color.White,
                fontSize = 11.sp,
                fontWeight = FontWeight.Medium,
                maxLines = 2,
                lineHeight = 13.sp
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ProfileScreenPreview() {
    val profileViewModel: ProfileViewModel = hiltViewModel()
    ProfileScreen(viewModel = profileViewModel)
}