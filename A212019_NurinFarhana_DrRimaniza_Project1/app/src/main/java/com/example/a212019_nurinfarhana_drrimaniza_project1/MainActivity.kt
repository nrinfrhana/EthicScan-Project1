package com.example.a212019_nurinfarhana_drrimaniza_project1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.example.a212019_nurinfarhana_drrimaniza_project1.navigation.NavGraph
import com.example.a212019_nurinfarhana_drrimaniza_project1.ui.theme.EthicScanTheme

class MainActivity : ComponentActivity() {
    private val viewModel: EthicViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            EthicScanTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    NavGraph(
                        navController = rememberNavController(),
                        viewModel     = viewModel
                    )
                }
            }
        }
    }
}