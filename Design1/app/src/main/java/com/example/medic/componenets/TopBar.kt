package com.example.medic.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun TopBar() {
    var selectedLocation by remember { mutableStateOf("Select") }
    var expanded by remember { mutableStateOf(false) }
    var searchQuery by remember { mutableStateOf("") }

    val countries = remember {
        listOf(
            "USA, NewYork", "Canada, Alaska", "Germany, Berlin", "France, Paris",
            "UK, London", "Romania, Bucharest", "India, Mumbai"
        )
    }

    val filteredCountries = countries.filter { it.contains(searchQuery, ignoreCase = true) }

    Column {
        // Location Selector Text and Row for selection
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                // Location Label
                Text(
                    text = "Location",
                    color = Color(0xFF6B7280),
                    fontWeight = FontWeight.W400,
                    fontSize = 16.sp,
                    lineHeight = 21.sp
                )
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.clickable { expanded = !expanded }

                ) {
                    Icon(Icons.Default.LocationOn, contentDescription = "Location On")
                    Text(
                        text = selectedLocation,
                        color = Color(0xFF374151),
                        fontWeight = FontWeight.W600,
                        fontSize = 16.sp,
                        lineHeight = 21.sp
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Icon(
                        imageVector = if (expanded) Icons.Default.KeyboardArrowUp
                                        else Icons.Default.KeyboardArrowDown,
                        contentDescription = if (expanded) "Keyboard Arrow Up" else "Keyboard Arrow Down",
                        tint = Color(0xFF374151)
                    )
                }
            }
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .background(
                        Color(0xFFF4F4F4),
                        shape = CircleShape
                    ),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    Icons.Default.Notifications,
                    contentDescription = "Notifications",
                    tint = Color(0xFF4B5563)
                )
            }
        }

        // Dropdown Menu
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier
                .width(380.dp)
                .padding(horizontal = 8.dp)
                .background(Color.White)
                .offset(y = 0.dp)
        ) {
            // Search TextField
            BasicTextField(
                value = searchQuery,
                onValueChange = { searchQuery = it },
                modifier = Modifier
                    .padding(start = 8.dp, end = 8.dp)
                    .fillMaxWidth()
                    .height(65.dp),
                textStyle = LocalTextStyle.current.copy(color = Color.Black, fontSize = 18.sp),
                decorationBox = { innerTextField ->
                    Box(
                        Modifier
                            .fillMaxWidth()
                            .background(Color.White)
                            .padding(8.dp)
                            .border(
                                1.dp,
                                Color(0xFFe9e4e6),
                                MaterialTheme.shapes.small),
                        contentAlignment = Alignment.CenterStart
                    ) {
                        if (searchQuery.isEmpty()) {
                            Text(
                                "Search for a country...",
                                color = Color.Gray,
                                fontSize = 18.sp,
                                modifier = Modifier.align(Alignment.CenterStart).padding(8.dp)
                            )
                        }
                        Box(
                            Modifier
                                .fillMaxWidth()
                                .padding(8.dp)
                        ) {
                            innerTextField()
                        }
                    }
                }
            )

            // Show filtered countries or "No results found"
            if (filteredCountries.isEmpty()) {
                DropdownMenuItem(onClick = {}) {
                    Text("No results found", color = Color.Gray)
                }
            } else {
                filteredCountries.forEach { country ->
                    DropdownMenuItem(
                        onClick = {
                            selectedLocation = country
                            expanded = false
                            searchQuery = ""
                        }
                    ) {
                        Text(text = country)
                    }
                }
            }
        }
    }
}

