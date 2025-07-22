package com.example.bill.header

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.unit.dp
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

@Composable
fun HeaderCard() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(400.dp)
    ) {
        Canvas(modifier = Modifier.matchParentSize()) {
            val width = size.width
            val height = size.height

            val path = Path().apply {
                moveTo(0f, height * 0.3f)
                cubicTo(
                    width * 0.25f, height * 0.1f,
                    width * 0.75f, height * 0.5f,
                    width, height * 0.3f
                )
                lineTo(width, 0f)
                lineTo(0f, 0f)
                close()
            }

            drawPath(
                path = path,
                brush = Brush.linearGradient(
                    colors = listOf(Color(0xFFFF416C), Color(0xFFFF4B2B), Color(0xFFFC466B)),
                    start = Offset.Zero,
                    end = Offset.Infinite
                )
            )
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(24.dp)
        ) {
            Text(
                text = getGreetingMessage(),
                color = Color.White,
                style = MaterialTheme.typography.bodyLarge
            )
            Text(
                text = "Trọng Hiển",
                color = Color.White,
                style = MaterialTheme.typography.headlineSmall.copy(
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp // chỉnh cỡ chữ nhỏ hơn, ví dụ 16sp
                )
            )
        }
    }
}

fun getGreetingMessage(): String {
    val calendar = java.util.Calendar.getInstance()
    val hour = calendar.get(java.util.Calendar.HOUR_OF_DAY)

    return when (hour) {
        in 5..11 -> "Chào buổi sáng 🌅"
        in 12..16 -> "Chào buổi trưa ☀️"
        in 17..20 -> "Chào buổi chiều 🌇"
        else -> "Chào buổi tối 🌙"
    }
}