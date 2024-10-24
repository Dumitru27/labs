package com.example.medic.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import com.example.medic.screens.Banner
import com.google.accompanist.pager.*


@OptIn(ExperimentalPagerApi::class)
@Composable
fun PromoSlider(banners: List<Banner>) {
    val pagerState = rememberPagerState()

    Column(modifier = Modifier.fillMaxWidth()) {
        HorizontalPager(
            count = banners.size,
            state = pagerState,
            modifier = Modifier
                .width(380.dp)
                .fillMaxWidth()
                .height(198.dp)
        ) { page ->
            PromoBanner(banners[page], pagerState.currentPage)
        }
    }
}

@Composable
fun PromoBanner(banner: Banner, currentPage: Int) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(Color.White)
    ) {
        // Background Image
        Image(
            painter = rememberImagePainter(banner.image),
            contentDescription = null,
            modifier = Modifier.fillMaxSize().clip(RoundedCornerShape(16.dp)),
            contentScale = ContentScale.Crop
        )

        // Dark gradient overlay
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.verticalGradient(
                        colors = listOf(Color.Black.copy(alpha = 0.3f), Color.Transparent)
                    )
                )
                .clip(RoundedCornerShape(16.dp))
        )

        // Text and other components
        Column(
            modifier = Modifier
                .align(Alignment.CenterStart)
                .padding(16.dp, end = 160.dp)
        ) {
            Text(
                text = banner.title,
                style = TextStyle(
                    color = Color.White,
                    fontSize = 24.sp,
                    lineHeight = 34.sp,
                    fontWeight = FontWeight.W700
                )
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = banner.description,
                style = TextStyle(
                    color = Color.White,
                    fontSize = 16.sp,
                    lineHeight = 24.sp,
                    fontWeight = FontWeight.W400
                )
            )
        }

        // Pagination Dots at the bottom
        Row(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(10.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            repeat(4) { index ->
                val isSelected = currentPage == index

                // Animated size for dots
                val dotSize by animateDpAsState(
                    targetValue = if (isSelected) 30.dp else 6.5.dp,
                    animationSpec = tween(durationMillis = 300, easing = FastOutSlowInEasing)
                )

                // Animated color for dots
                val dotColor by animateColorAsState(
                    targetValue = if (isSelected) Color.White else Color(0xFFF4F4F4),
                    animationSpec = tween(durationMillis = 300, easing = FastOutSlowInEasing)
                )

                // Animated corner radius for shape
                val cornerRadius by animateDpAsState(
                    targetValue = if (isSelected) 50.dp else 0.dp,
                    animationSpec = tween(durationMillis = 300, easing = FastOutSlowInEasing)
                )

                // Animated elevation for shadow effect
                val elevation by animateDpAsState(
                    targetValue = if (isSelected) 4.dp else 0.dp,
                    animationSpec = tween(durationMillis = 300, easing = FastOutSlowInEasing)
                )

                // Opacity effect
                val opacity by animateFloatAsState(
                    targetValue = if (isSelected) 1f else 0.5f,
                    animationSpec = tween(durationMillis = 300, easing = FastOutSlowInEasing)
                )

                Box(
                    modifier = Modifier
                        .padding(horizontal = if (isSelected) 6.dp else 3.dp)
                        .height(6.dp)
                        .width(dotSize)
                        .background(
                            color = dotColor.copy(alpha = opacity),
                            shape = CircleShape
                        )
                        .shadow(elevation, RoundedCornerShape(cornerRadius))
                )
            }
        }
        // Draw the circle
        Canvas(modifier = Modifier.fillMaxSize()) {
            when (currentPage) {
                0 -> {
                    // Page 1
                    drawCircle(
                        color = Color.White.copy(alpha = 0.2f),
                        radius = 250f,
                        center = Offset(100f, 60f)
                    )

                    drawCircle(
                        color = Color.White.copy(alpha = 0.2f),
                        radius = 132f,
                        center = Offset(320f, 615f)
                    )
                }
                1 -> {
                    // Page 2
                    drawCircle(
                        color = Color.White.copy(alpha = 0.2f),
                        radius = 250f,
                        center = Offset(300f, 360f)
                    )
                    drawCircle(
                        color = Color.White.copy(alpha = 0.2f),
                        radius = 132f,
                        center = Offset(120f, 115f)
                    )
                }
                2 -> {
                    // Page 3
                    drawCircle(
                        color = Color.White.copy(alpha = 0.2f),
                        radius = 250f,
                        center = Offset(200f, 100f)
                    )

                    drawCircle(
                        color = Color.White.copy(alpha = 0.2f),
                        radius = 132f,
                        center = Offset(320f, 515f)
                    )
                }
                3 -> {
                    // Page 4
                    drawCircle(
                        color = Color.White.copy(alpha = 0.2f),
                        radius = 250f,
                        center = Offset(260f, 150f)
                    )

                    drawCircle(
                        color = Color.White.copy(alpha = 0.2f),
                        radius = 132f,
                        center = Offset(1050f, 115f)
                    )
                }
            }
        }

    }
}

