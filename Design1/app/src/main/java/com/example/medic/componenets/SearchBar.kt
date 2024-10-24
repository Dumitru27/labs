package com.example.medic.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun SearchBar() {

    var searchText by remember { mutableStateOf("") }

    TextField(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp, vertical = 0.dp)
            .height(50.dp),
        value = searchText,
        onValueChange = { newText ->
            searchText = newText
        },
        placeholder = {
            Text(
                text = "Search doctor...",
                color = Color(0xFF9CA3AF),
                fontSize = 16.sp,
                fontWeight = FontWeight.W400,
                lineHeight = 21.sp
            )
        },
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = "Search Icon",
                tint = Color(0xFF9CA3AF)
            )
        },
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = Color(0xFFF3F4F6),
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            cursorColor = Color.Black
        ),

        textStyle = TextStyle(
            color = Color.Black,
            fontSize = 17.sp,
            fontWeight = FontWeight.W500,
            lineHeight = 21.sp
        ),
        shape = RoundedCornerShape(8.dp)
    )
}
