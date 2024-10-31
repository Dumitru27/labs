package com.example.medic.domain

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