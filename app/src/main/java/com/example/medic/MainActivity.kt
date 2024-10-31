package com.example.medic

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.medic.presentation.HomeScreen
import com.example.medic.ui.theme.MedicTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MedicTheme {
                HomeScreen()
            }
        }
    }
}
