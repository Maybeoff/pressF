package ru.mby.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import ru.mby.myapplication.ui.theme.MyApplicationTheme
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import ru.mby.myapplication.ui.details.DetailsScreen
import ru.mby.myapplication.ui.home.HomeScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyApplicationTheme {
                val navController = rememberNavController()
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    NavHost(
                        navController = navController,
                        startDestination = "home",
                        modifier = Modifier.padding(innerPadding)
                    ) {
                        composable("home") {
                            HomeScreen(onOpenDetails = { id ->
                                navController.navigate("details/$id")
                            })
                        }
                        composable("details/{id}") { backStackEntry ->
                            val id = backStackEntry.arguments?.getString("id").orEmpty()
                            DetailsScreen(appId = id)
                        }
                    }
                }
            }
        }
    }
}
