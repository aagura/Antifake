package com.example.antifake.data.model

data class AnalysisReport(
    val isFake: Boolean,
    val reason: String,
    val c2paFound: Boolean,
    val apiScore: Int,
    val internetReputation: Int
)
