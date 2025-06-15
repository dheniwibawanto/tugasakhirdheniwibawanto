package com.example.tugasakhirdheniwibawanto

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun Navigation(navController: NavHostController) {
    NavHost(navController = navController, startDestination = "homescreen") {
        composable("homescreen") {
            HomeScreen(navController = navController)
        }
        composable("profilescreen") {
            ProfileScreen(navController = navController)
        }
        composable("detail/{foodName}/{imageRes}") { backStackEntry ->
            val foodName = backStackEntry.arguments?.getString("foodName") ?: "Tidak Diketahui"
            val imageRes = backStackEntry.arguments?.getString("imageRes")?.toIntOrNull() ?: R.drawable.kuliner
            FoodDetailScreen(foodName = foodName, imageRes = imageRes, navController = navController)
        }
    }
}
