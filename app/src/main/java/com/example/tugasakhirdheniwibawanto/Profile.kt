package com.example.tugasakhirdheniwibawanto

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.tugasakhirdheniwibawanto.ui.theme.TugasAkhirDheniWibawantoTheme

@Composable
fun ProfileScreen(navController: NavController) {
    ProfileContent(navController = navController)
}

@Composable
fun ProfileContent(navController: NavController) {
    val msg = navController.currentBackStackEntry?.savedStateHandle?.get<String>("msg")

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(R.drawable.profile),
            contentDescription = null,
            modifier = Modifier.size(100.dp).clip(CircleShape),
            contentScale = ContentScale.Crop
        )
        Text(
            text = stringResource(R.string.profile),
            modifier = Modifier.paddingFromBaseline(top = 24.dp, bottom = 8.dp),
            style = MaterialTheme.typography.bodyMedium
        )
        Text(
            text = "Hai, Nama saya Dheni Wibawanto.",
            modifier = Modifier.padding(30.dp),
            overflow = TextOverflow.Clip,
            maxLines = 5,
            color = Color.Black,
            fontSize = 16.sp,
            fontFamily = FontFamily.Monospace,
            letterSpacing = 2.sp,
            textAlign = TextAlign.Center
        )
        Button(onClick = { navController.navigate("homescreen") }) {
            Text("Go to Home Screen")
        }
        Spacer(modifier = Modifier.height(8.dp))
        msg?.let {
            Text(it)
        }
    }
}
