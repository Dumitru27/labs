package com.example.medic.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.materialIcon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import com.example.medic.R
import com.example.medic.screens.Doctor


@Composable
fun MedicalCenterCard(modifier: Modifier = Modifier, doctors: List<Doctor>) {

    Column(modifier = modifier.padding(top = 3.dp)) {
        // Header Row with "Found" and "Settings"
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "${doctors.size} found",
                style = TextStyle(
                    color = Color.Black,
                    fontSize = 22.sp,
                    fontWeight = FontWeight.W700
                )
            )

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Default",
                    style = TextStyle(
                        color = Color(0xFF6B7280),
                        fontSize = 19.sp,
                        fontWeight = FontWeight.W500,
                    )
                )

                Spacer(modifier = Modifier.width(5.dp))

                // Clickable settings icon
                Icon(
                    painter = painterResource(id = R.drawable.settings),
                    contentDescription = "Settings",
                    tint = Color(0xFF6B7280),
                    modifier = Modifier.size(18.dp)
                )
            }
        }

        Spacer(modifier = Modifier.height(12.dp))

        // List of medical centers using LazyColumn
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .height(405.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            items(doctors.size) { index ->
                DoctorListItem(doctors[index])
            }
        }
    }
}

@Composable
fun DoctorListItem(doctors: Doctor) {
    var isFav by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .padding(start = 5.dp, end = 5.dp)
            .size(width = 370.dp, height = 152.dp)
            .shadow(
                elevation = 7.dp,
                shape = RoundedCornerShape(16.dp)
            )
            .offset(y = 2.dp, x = -2.dp)
            .border(
                width = 1.dp,
                color = Color(0xFFF3F3F3),
                shape = RoundedCornerShape(16.dp)
            )
            .background(
                color = Color(0xFFFBFBFB),
                shape = RoundedCornerShape(16.dp)
            )
    ) {
        Row(
            modifier = Modifier.fillMaxSize(),
        ) {
            Box(
                modifier = Modifier
                    .size(150.dp)
                    .padding(start = 15.dp, top = 14.dp, bottom = 14.dp, end = 14.dp)
            ){
                Image(
                    painter = rememberImagePainter(doctors.image),
                    contentDescription = "Doctor Image",
                    modifier = Modifier
                        .size(130.dp)
                        .clip(RoundedCornerShape(16.dp))
                )
            }

            // Doctor Details
            Column(
                modifier = Modifier
                    .padding(top = 14.dp, start = 0.dp, end = 12.dp)
                    .fillMaxSize()
            ) {
                Row() {
                    Text(
                        text = doctors.full_name,
                        style = TextStyle(
                            color = Color.Black,
                            fontSize = 22.sp,
                            fontWeight = FontWeight.W700
                        )
                    )
                    Spacer(modifier = Modifier.width(50.dp))
                    Icon(
                        imageVector = if (isFav) Icons.Default.Favorite else Icons.Default.FavoriteBorder, // Correct usage
                        contentDescription = "Favorite",
                        modifier = Modifier
                            .size(20.dp)
                            .clickable {
                                isFav = !isFav
                            },
                        tint = Color(if (isFav) 0xFFEA0000 else 0xFF000000) // Correct usage of color condition
                    )
                }

                Spacer(modifier = Modifier.height(8.dp))

                Divider(
                    color = Color(0xFFE5E7EB),
                    thickness = 1.dp
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = doctors.type_of_doctor,
                    style = TextStyle(
                        color = Color(0xFF4B5563),
                        fontSize = 19.25.sp,
                        fontWeight = FontWeight.W600,
                        lineHeight = 29.sp
                    )
                )

                Spacer(modifier = Modifier.height(5.dp))

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.pin),
                        contentDescription = "Location",
                        modifier = Modifier.size(15.dp)
                    )

                    Text(
                        text = doctors.location_of_center,
                        style = TextStyle(
                            color = Color(0xFF4B5563),
                            fontSize = 19.25.sp,
                            fontWeight = FontWeight.W400,
                            lineHeight = 29.sp,
                            letterSpacing = (-0.6).sp
                        ),
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )

                }

                Spacer(modifier = Modifier.height(5.dp))

                Row {
                    // Stars
                    Icon(
                        painter = painterResource(id = R.drawable.star ),
                        contentDescription = "Star",
                        modifier = Modifier.size(15.dp),
                        tint = Color(0xFFF59E0B)
                    )

                    Spacer(modifier = Modifier.width(5.dp))

                    Text(
                        text = "${doctors.review_rate}",
                        style = TextStyle(
                            color = Color(0xFF6B7280),
                            fontSize = 16.5.sp,
                            fontWeight = FontWeight.W400,
                            lineHeight = 25.sp
                        )
                    )

                    Spacer(modifier = Modifier.width(9.dp))

                    Box(
                        modifier = Modifier
                            .height(17.dp)
                            .width(1.dp)
                            .background(Color(0xFFE5E7EB))
                    )

                    Spacer(modifier = Modifier.width(9.dp))

                    Text(
                        text = "${doctors.reviews_count} Reviews",
                        style = TextStyle(
                            color = Color(0xFF6B7280),
                            fontSize = 16.5.sp,
                            fontWeight = FontWeight.W400,
                            lineHeight = 25.sp
                        )
                    )
                }
            }
        }
    }
}
