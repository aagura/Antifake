package com.example.antifake.ui

import android.net.Uri
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.antifake.data.model.AnalysisReport
import com.example.antifake.domain.AnalyzeVideoUseCase

@Composable
fun AnalysisResultScreen(videoUri: Uri) {

    // результат анализа (изначально null)
    var result by remember { mutableStateOf<AnalysisReport?>(null) }

    // запускаем анализ один раз при открытии экрана
    LaunchedEffect(videoUri) {
        val useCase = AnalyzeVideoUseCase()
        result = useCase.analyze(videoUri)   // <-- ВАЖНО: здесь возвращается AnalysisReport
    }

    Scaffold { padding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding),
            contentAlignment = Alignment.Center
        ) {

            if (result == null) {
                CircularProgressIndicator()
            } else {
                ResultContent(result!!)
            }
        }
    }
}

@Composable
private fun ResultContent(report: AnalysisReport) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(24.dp)
    ) {

        Text(
            text = if (report.isFake) "⚠ Видео подозрительное" else "✔ Видео кажется настоящим",
            style = MaterialTheme.typography.headlineSmall
        )

        Spacer(Modifier.height(16.dp))

        Text("Причина: ${report.reason}")
        Text("C2PA найден: ${report.c2paFound}")
        Text("API оценка: ${report.apiScore}")
        Text("Репутация источника: ${report.internetReputation}")
    }
}
