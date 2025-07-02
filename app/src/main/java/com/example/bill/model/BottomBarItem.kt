package com.example.bill.model

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.ui.graphics.vector.ImageVector

data class BottomBarItem(
    val label: String,
    val icon: ImageVector
)

val bottomNavItems = listOf(
    BottomBarItem("Home", Icons.Default.Home),
    BottomBarItem("Profile", Icons.Default.Person),
    BottomBarItem("Activity", Icons.Default.Favorite),
)