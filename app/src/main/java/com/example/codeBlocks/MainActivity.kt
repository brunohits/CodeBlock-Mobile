package com.example.codeBlocks

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.ui.*
import androidx.compose.ui.graphics.Color
import com.example.codeBlocks.ui.theme.OurMobileTheme


class MainActivity : ComponentActivity() {
    private var pixelsPerDp: Float = 0f
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        pixelsPerDp = resources.displayMetrics.density
        setContent {
            OurMobileTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Color.DarkGray
                ) {
                    MyScreen()
                }
            }
        }
    }
}
