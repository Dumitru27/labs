package com.example.medic.presentation

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.medic.components.*
import com.example.medic.data.readJsonFromRaw
import com.example.medic.data.parseJson
import com.example.medic.domain.Data
import com.example.medic.R

@Composable
fun HomeScreen(modifier: Modifier = Modifier) {
    val scrollState = rememberScrollState()

    val context = LocalContext.current

    val data = remember {
        try {
            val jsonString = readJsonFromRaw(context)
            parseJson(jsonString)
        } catch (e: Exception) {
            Data(emptyList(), emptyList(), emptyList(), emptyList())
        }
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
            .padding(16.dp)
    ) {
        TopBar()
        Spacer(modifier = Modifier.height(5.dp))
        SearchBar()
        Spacer(modifier = Modifier.height(16.dp))
        PromoSlider(banners = data.banners)
        Spacer(modifier = Modifier.height(20.dp))
        CategoriesSection(categories = data.categories)
        Spacer(modifier = Modifier.height(20.dp))
        NearbyMedicalCenters(centers = data.nearby_centers)
        Spacer(modifier = Modifier.height(20.dp))
        MedicalCenterCard(doctors = data.doctors)
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewHomeScreen() {
    HomeScreen()
}