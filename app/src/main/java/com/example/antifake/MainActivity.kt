package com.example.antifake

import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.antifake.ui.ResultScreen
import com.example.antifake.ui.UploadScreen

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            // Запускаем первый экран — загрузка видео
            UploadScreen { selectedUriString ->
                // Передаём сюда строку вида "content://..."
                val uri = Uri.parse(selectedUriString)

                // Вызываем ResultScreen ТОЛЬКО внутри Compose
                ResultScreen(videoUri = uri)
            }
        }
    }
}
