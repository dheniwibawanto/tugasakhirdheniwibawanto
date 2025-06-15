package com.example.tugasakhirdheniwibawanto

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.tugasakhirdheniwibawanto.ui.theme.TugasAkhirDheniWibawantoTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TugasAkhirDheniWibawantoTheme {
                Navigation()
            }
        }
    }
}

// ====================================
// 🍽️ DESKRIPSI MAKANAN (Tulis Di Sini)
// ====================================
private val foodDescriptions = mapOf(
    "Bakso" to "Bakso kenyal berkuah gurih, cocok dinikmati saat cuaca dingin.",
    "Salad" to "Perpaduan sayur segar, warna-warni, kaya vitamin dan rasa.",
    "Soto" to "Soto dengan kuah kuning harum dan daging empuk, khas Indonesia.",
    "Lalapan" to "Lalapan segar lengkap dengan sambal dan nasi hangat.",
    "Lumpia" to "Lumpia goreng berisi sayuran dan ayam, renyah di luar, lembut di dalam.",
    "Batagor" to "Batagor Bandung favorit dengan saus kacang pedas manis.",
    "Mie Ayam" to "Mie Ayam kenyal dan gurih, lengkap dengan topping ayam kecap.",
    "Art Culinary" to "Art Culinary adalah seni memasak yang memadukan rasa dan estetika."
)

@Composable
fun SearchBar(modifier: Modifier = Modifier) {
    TextField(
        value = "",
        onValueChange = {},
        leadingIcon = {
            Icon(imageVector = Icons.Default.Search, contentDescription = null)
        },
        colors = TextFieldDefaults.colors(
            unfocusedContainerColor = MaterialTheme.colorScheme.surface,
            focusedContainerColor = MaterialTheme.colorScheme.surface
        ),
        placeholder = {
            Text(stringResource(R.string.placeholder_search))
        },
        modifier = modifier.fillMaxWidth().heightIn(min = 56.dp)
    )
}

@Composable
fun AlignYourBodyElement(
    @DrawableRes drawable: Int,
    @StringRes text: Int,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {}
) {
    Column(
        modifier = modifier.clickable(onClick = onClick),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(drawable),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.size(88.dp).clip(CircleShape)
        )
        Text(
            text = stringResource(text),
            modifier = Modifier.paddingFromBaseline(top = 24.dp, bottom = 8.dp),
            style = MaterialTheme.typography.bodyMedium
        )
    }
}

@Composable
fun FavoriteCollectionCard(
    @DrawableRes drawable: Int,
    @StringRes text: Int,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {}
) {
    Surface(
        shape = MaterialTheme.shapes.medium,
        color = MaterialTheme.colorScheme.surfaceVariant,
        modifier = modifier.clickable(onClick = onClick)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.width(255.dp)
        ) {
            Image(
                painter = painterResource(drawable),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.size(80.dp)
            )
            Text(
                text = stringResource(text),
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(horizontal = 16.dp)
            )
        }
    }
}

private data class DrawableStringPair(
    @DrawableRes val drawable: Int,
    @StringRes val text: Int
)

private val alignYourBodyData = listOf(
    R.drawable.bakso to R.string.bakso,
    R.drawable.salad to R.string.salad,
    R.drawable.soto to R.string.soto,
    R.drawable.lalapan to R.string.lalapan
).map { DrawableStringPair(it.first, it.second) }

private val favoriteCollectionsData = listOf(
    R.drawable.kumpulankuliner to R.string.kumpulankuliner,
    R.drawable.kuliner to R.string.kuliner,
    R.drawable.batagor to R.string.batagor,
    R.drawable.lumpia to R.string.lumpia
).map { DrawableStringPair(it.first, it.second) }

@Composable
fun AlignYourBodyRow(modifier: Modifier = Modifier, navController: NavController) {
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = PaddingValues(horizontal = 16.dp),
        modifier = modifier
    ) {
        items(alignYourBodyData) { item ->
            val label = stringResource(item.text)
            AlignYourBodyElement(item.drawable, item.text) {
                navController.navigate("detail/$label/${item.drawable}")
            }
        }
    }
}

@Composable
fun FavoriteCollectionsGrid(modifier: Modifier = Modifier, navController: NavController) {
    LazyHorizontalGrid(
        rows = GridCells.Fixed(2),
        contentPadding = PaddingValues(horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = modifier.height(168.dp)
    ) {
        items(favoriteCollectionsData) { item ->
            val label = stringResource(item.text)
            FavoriteCollectionCard(item.drawable, item.text) {
                navController.navigate("detail/$label/${item.drawable}")
            }
        }
    }
}

@Composable
fun HomeSection(
    @StringRes title: Int,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    Column(modifier) {
        Text(
            text = stringResource(title),
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.paddingFromBaseline(top = 40.dp, bottom = 16.dp)
                .padding(horizontal = 16.dp)
        )
        content()
    }
}

@Composable
fun HomeScreen(modifier: Modifier = Modifier, navController: NavController) {
    Column(modifier.verticalScroll(rememberScrollState())) {
        Spacer(Modifier.height(16.dp))
        SearchBar(Modifier.padding(horizontal = 16.dp))
        HomeSection(title = R.string.kumpulankuliner, content = {
            AlignYourBodyRow(navController = navController)
        })
        HomeSection(title = R.string.Makanan_Favorit, content = {
            FavoriteCollectionsGrid(navController = navController)
        })
        Spacer(Modifier.height(16.dp))
    }
}

@Composable
fun BottomNavigation(navController: NavController) {
    NavigationBar(containerColor = MaterialTheme.colorScheme.surfaceVariant) {
        NavigationBarItem(
            icon = { Icon(Icons.Default.Home, contentDescription = null) },
            label = { Text(stringResource(R.string.bottom_navigation_home)) },
            selected = true,
            onClick = { navController.navigate("homescreen") }
        )
        NavigationBarItem(
            icon = { Icon(Icons.Default.AccountCircle, contentDescription = null) },
            label = { Text(stringResource(R.string.bottom_navigation_profile)) },
            selected = false,
            onClick = { navController.navigate("profilescreen") }
        )
    }
}

@Composable
fun FoodDetailScreen(foodName: String, @DrawableRes imageRes: Int, navController: NavController) {
    Scaffold(bottomBar = { BottomNavigation(navController) }) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "Detail Makanan", style = MaterialTheme.typography.headlineSmall)
            Spacer(modifier = Modifier.height(16.dp))
            Image(
                painter = painterResource(id = imageRes),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .height(200.dp)
                    .fillMaxWidth()
                    .clip(MaterialTheme.shapes.medium)
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = "Nama: $foodName", style = MaterialTheme.typography.titleLarge)
            Spacer(modifier = Modifier.height(8.dp))

            val description = foodDescriptions[foodName] ?: "Deskripsi belum tersedia."
            Text(text = description)

            Spacer(modifier = Modifier.height(24.dp))
            Button(onClick = { navController.navigate("homescreen") }) {
                Text("Go to Home Screen")
            }
        }
    }
}
