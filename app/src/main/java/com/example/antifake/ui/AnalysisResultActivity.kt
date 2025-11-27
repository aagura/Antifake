package com.example.antifake.ui

import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme

class AnalysisResultActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val videoUri = intent.getStringExtra("video_uri")

        setContent {
            MaterialTheme {
                if (videoUri != null) {
                    AnalysisResultScreen(Uri.parse(videoUri))
                } else {
                    // ошибка: uri не передан
                }
            }
        }
    }
}
