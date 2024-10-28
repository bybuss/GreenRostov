package bob.colbaskin.greenrostov.ui.screens.home

import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import bob.colbaskin.greenrostov.AuthActivity
import bob.colbaskin.greenrostov.R
import bob.colbaskin.greenrostov.domain.network.restApi.RestApiRepository
import bob.colbaskin.greenrostov.ui.screens.auth.AuthViewModel
import bob.colbaskin.greenrostov.ui.screens.auth.CustomTextField


/**
 * @author bybuss
 */

@Composable
fun HomeScreen (
    modifier: Modifier = Modifier,
    authViewModel: AuthViewModel
) {
    val context = LocalContext.current
    var showDialog by remember { mutableStateOf(false) }
    var code by remember { mutableStateOf("") }

    Column (
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TextButton(onClick = {
            authViewModel.signOut()

            val intent = Intent(context, AuthActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            context.startActivity(intent)
        }) {
            Text(text = "Выйти из аккаунта")
        }
        Button(onClick = { showDialog = !showDialog}) {

        }
        if (showDialog) {
            AlertDialog(
                onDismissRequest = { showDialog = false },
                confirmButton = {
                    Button(onClick = {  }) {
                        Text("Отправить код")
                    }
                },
                dismissButton = {
                    Button(onClick = { showDialog = false}) {
                        Text("Закрыть")
                    }
                },
                title = {
                    Text(
                        text = "Проверьте вашу почту и введите код!",
                        color = Color.White,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold
                    )
                },
                text = {
                    Column(
                        modifier = Modifier.padding(16.dp)
                    ) {
                        CustomTextField(label = "Code", value = code, onValueChange = { code = it })
                    }
                },
                containerColor = Color.Transparent,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}