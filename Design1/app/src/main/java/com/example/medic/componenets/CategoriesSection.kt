package com.example.medic.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.res.painterResource
import coil.compose.rememberImagePainter
import com.example.medic.R
import com.example.medic.screens.Categorie

@Composable
fun CategoriesSection(categories: List<Categorie>) {
    var seeAll by remember { mutableStateOf(false) }

    Column(modifier = Modifier.padding(top = 3.dp)) {
        // Header Row with "Categories" and "See All"
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Categories",
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

        Spacer(modifier = Modifier.height(12.2.dp))

        // Fix grid height to avoid nested scroll conflict
        LazyVerticalGrid(
            columns = GridCells.Fixed(4),
            modifier = Modifier
                .fillMaxWidth()
                .height(205.dp),
            horizontalArrangement = Arrangement.spacedBy(33.dp),
            verticalArrangement = Arrangement.spacedBy(19.dp)
        ) {
            items(categories.take(8).size) { index ->
                CategoryItem(categories[index])
            }
        }
    }
    if (seeAll) {
        CategoryDialog(categories) { seeAll = false }
    }
}


@Composable
fun CategoryItem(categories: Categorie) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .clickable { /*Amma add later*/}
    ) {
        val iconSize = if (categories.title == "General") 60.dp else 40.dp

        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .size(70.64.dp)
                .clip(RoundedCornerShape(9.76.dp))
                .background(Color((0xFF000000..0xFFFFFFFF).random().toInt()))
        ) {
            Canvas(modifier = Modifier.fillMaxSize()) {
                drawCircle(
                    color = Color.White.copy(alpha = 0.2f),
                    radius = 90f,
                    center = Offset(10f, 4f)
                )
            }
            Icon(
                painter = painterResource(id = getDrawableResource(categories.icon)),
                contentDescription = categories.title,
                tint = Color.White,
                modifier = Modifier.size(iconSize)
            )
        }

        Spacer(modifier = Modifier.height(4.88.dp))

        Text(
            text = categories.title,
            style = TextStyle(
                color = Color(0xFF4B5563),
                fontSize = 16.sp,
                lineHeight = 21.96.sp,
                fontWeight = FontWeight.W700
            ),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
    }
}

fun getDrawableResource(iconName: String): Int {
    return when (iconName) {
        "heartrate" -> R.drawable.heartrate
        "toothanatomy" -> R.drawable.toothanatomy
        "broken_bone" -> R.drawable.broken_bone
        "derma" -> R.drawable.derma
        "pediatric" -> R.drawable.pediatric
        "eye" -> R.drawable.eye
        else -> R.drawable.flask
    }
}

@Composable
fun CategoryDialog(
    categories: List<Categorie>,
    onDismiss: () -> Unit
) {
    Dialog(onDismissRequest = onDismiss) {
        Surface(
            shape = RoundedCornerShape(16.dp),
            color = Color(0xFFEFEFEF),
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(top = 150.dp, bottom = 150.dp)
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                // Dialog Title
                Text(
                    text = "All Categories",
                    style = TextStyle(
                        color = Color.Black,
                        fontSize = 22.sp,
                        lineHeight = 30.sp,
                        fontWeight = FontWeight.W700
                    ),
                    modifier = Modifier
                        .padding(bottom = 16.dp)
                        .align(Alignment.CenterHorizontally)
                )

                // Scrollable list of categories
                LazyVerticalGrid(
                    columns = GridCells.Fixed(3),
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f),
                    contentPadding = PaddingValues(8.dp),
                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    items(categories.size) { index ->
                        CategoryItem(categories[index])
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Button to close the dialog
                Button(
                    onClick = onDismiss,
                    modifier = Modifier.align(Alignment.End)
                ) {
                    Text(
                        text = "Close",
                        style = TextStyle(
                            color = Color.White,
                            fontSize = 16.sp,
                            lineHeight = 21.96.sp,
                            fontWeight = FontWeight.W700
                        )
                    )
                }
            }
        }
    }
}
