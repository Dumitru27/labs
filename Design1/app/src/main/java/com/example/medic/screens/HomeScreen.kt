package com.example.medic.screens

import android.content.Context
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
import com.google.gson.Gson
import com.example.medic.R

data class Data(
    val banners: List<Banner>,
    val categories: List<Categorie>,
    val nearby_centers: List<NearbyCenter>,
    val doctors: List<Doctor>
)

data class Banner(
    val title: String,
    val description: String,
    val image: String
)

data class Categorie(
    val title: String,
    val icon: String
)

data class NearbyCenter(
    val image: String,
    val title: String,
    val location_name: String,
    val review_rate: Float,
    val count_reviews: Int,
    val distance_km: Float,
    val distance_minutes: Int
)

data class Doctor(
    val image: String,
    val full_name: String,
    val type_of_doctor: String,
    val location_of_center: String,
    val review_rate: Float,
    val reviews_count: Int
)

// Read from json file
fun readJsonFromRaw(context: Context): String {
    val inputStream = context.resources.openRawResource(R.raw.data)
    return inputStream.bufferedReader().use { it.readText() }
}

// Parse json file
fun parseJson(jsonString: String): Data {
    val gson = Gson()
    return gson.fromJson(jsonString, Data::class.java)
}

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
