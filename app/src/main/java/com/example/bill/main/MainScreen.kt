package com.example.bill.main

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.ui.Modifier
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.tooling.preview.Preview
import com.example.bill.loading.FullScreenLoading

@Preview(showBackground = true)
@Composable
fun MainScreen() {
    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier.align(Alignment.Center),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("Ấn nút để hiển thị loading")

            Button(onClick = {

            }) {
                Text(text = "Click")
            }
        }
        FullScreenLoading(true)
        // Hiển thị loading khi biến true
    }
}
