package com.example.antifake.domain

import android.net.Uri
import com.example.antifake.data.model.AnalysisReport

class AnalyzeVideoUseCase {

    suspend fun analyze(videoUri: Uri): AnalysisReport {
        // Заглушка вместо реального анализа
        return AnalysisReport(
            isFake = false,
            reason = "Данных недостаточно",
            c2paFound = false,
            apiScore = 75,
            internetReputation = 60
        )
    }
}
