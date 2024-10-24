package com.example.medic.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.medic.R
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.layout.ContentScale
import coil.compose.rememberImagePainter
import com.example.medic.screens.NearbyCenter

@Composable
fun NearbyMedicalCenters(centers: List<NearbyCenter>) {

    var seeAll by remember { mutableStateOf(false) }

    Column(modifier = Modifier.padding(top = 3.dp)) {
    }
        // Header Row with "Categories" and "See All"
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Nearby Medical Centers",
                style = TextStyle(
                    color = Color.Black,
                    fontSize = 22.sp,
                    lineHeight = 30.sp,
                    fontWeight = FontWeight.W700
                )
            )
            Text(
                text = if (seeAll) "Show Less" else "See All",
                style = TextStyle(
                    color = Color(0xFF6B7280),
                    fontSize = 19.sp,
                    lineHeight = 26.sp,
                    fontWeight = FontWeight.W500,
                ),
                modifier = Modifier.clickable { seeAll = !seeAll }
            )
        }
    Spacer(modifier = Modifier.height(12.dp))

    LazyRow (){
        items(centers.size) { index ->
            MedicalCenterCard(centers[index])
        }
    }
}

@Composable
fun MedicalCenterCard(centers: NearbyCenter) {
    Box(
        modifier = Modifier
            .padding(start = 1.dp, end = 20.dp)
            .size(width = 250.dp, height = 272.dp)
            .shadow(
                elevation = 4.dp,
                shape = RoundedCornerShape(12.dp)
            )
            .offset(y = 2.dp, x = -2.dp)
            .border(
                width = 1.dp,
                color = Color(0xFFF3F3F3),
                shape = RoundedCornerShape(12.dp)
            )
            .background(
                color = Color(0xFFFBFBFB),
                shape = RoundedCornerShape(12.dp)
            )
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .size(width = 250.dp, height = 130.dp)
            ) {
                // Image
                Image(
                    painter = rememberImagePainter(centers.image),
                    contentDescription = null,
                    modifier = Modifier
                        .size(width = 250.dp, height = 130.dp)
                        .clip(
                            RoundedCornerShape(
                                topStart = 12.dp,
                                topEnd = 12.dp,
                                bottomStart = 0.dp,
                                bottomEnd = 0.dp
                            )
                        ), // Round only the top corners
                    contentScale = ContentScale.Crop
                )
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 14.dp, start = 18.dp, end = 18.dp, bottom = 16.dp),
            ) {
                Column() {
                    // Name
                    Text(
                        text = centers.title,
                        style = TextStyle(
                            color = Color(0xFF4B5563),
                            fontSize = 18.sp,
                            lineHeight = 24.sp,
                            fontWeight = FontWeight.W700
                        )
                    )

                    Spacer(modifier = Modifier.height(10.dp))

                    // Location
                    Row {
                        Icon(
                            painter = painterResource(id = R.drawable.pin),
                            contentDescription = null,
                            tint = Color(0xFF6B7280),
                            modifier = Modifier.size(16.dp)
                        )
                        Text(
                            text = centers.location_name,
                            style = TextStyle(
                                color = Color(0xFF6B7280),
                                fontSize = 15.42.sp,
                                lineHeight = 20.sp,
                                fontWeight = FontWeight.W400
                            )
                        )
                    }

                    Spacer(modifier = Modifier.height(4.dp))

                    // Stars and Reviews
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        // Stars
                        Text(
                            text = "${centers.review_rate}",
                            style = TextStyle(
                                color = Color(0xFF6B7280),
                                fontSize = 15.42.sp,
                                lineHeight = 20.sp,
                                fontWeight = FontWeight.W600
                            )
                        )

                        Spacer(modifier = Modifier.width(8.dp))

                        repeat(centers.review_rate.toInt()) {
                            Icon(
                                painter = painterResource(id = R.drawable.star),
                                contentDescription = null,
                                tint = Color(0xFFF59E0B),
                                modifier = Modifier.size(12.dp)
                            )
                            Spacer(modifier = Modifier.width(2.dp))
                        }

                        Spacer(modifier = Modifier.width(8.dp))

                        // Reviews
                        Text(
                            text = "(${centers.count_reviews} reviews)",
                            style = TextStyle(
                                color = Color(0xFF6B7280),
                                fontSize = 15.42.sp,
                                lineHeight = 20.sp,
                                fontWeight = FontWeight.W400
                            )
                        )
                    }

                    Spacer(modifier = Modifier.height(12.dp))

                    Divider(
                        color = Color(0xFFE5E7EB),
                        thickness = 1.dp
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    Row {
                        Icon(
                            painter = painterResource(id = R.drawable.location),
                            contentDescription = null,
                            tint = Color(0xFF6B7280),
                            modifier = Modifier.size(16.dp)
                        )

                        Spacer(modifier = Modifier.width(4.dp))

                        Text(
                            text = centers.distance_km.toString() + " km away" + " - " + centers.distance_minutes.toString() + " min",
                            style = TextStyle(
                                color = Color(0xFF6B7280),
                                fontSize = 15.42.sp,
                                lineHeight = 20.sp,
                                fontWeight = FontWeight.W400
                            )
                        )

                        Spacer(modifier = Modifier.width(43.dp))

                        Icon(
                            painter = painterResource(id = R.drawable.type),
                            contentDescription = null,
                            tint = Color(0xFF6B7280),
                            modifier = Modifier.size(16.dp)
                        )

                        Spacer(modifier = Modifier.width(4.dp))

                        // Type
                        Text(
                            text = "Hospital",
                            style = TextStyle(
                                color = Color(0xFF6B7280),
                                fontSize = 15.42.sp,
                                lineHeight = 20.sp,
                                fontWeight = FontWeight.W400
                            )
                        )
                    }
                }
            }
        }
    }
}


