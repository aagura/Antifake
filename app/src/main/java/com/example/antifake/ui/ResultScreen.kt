package com.example.antifake.ui

import android.content.Intent
import android.net.Uri
import androidx.activity.ComponentActivity
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.viewinterop.AndroidView
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.ui.PlayerView

@Composable
fun ResultScreen(videoUri: Uri?) {
    val context = LocalContext.current

    // ExoPlayer instance
    val player = remember {
        SimpleExoPlayer.Builder(context).build()
    }

    // Load selected video into player
    LaunchedEffect(videoUri) {
        if (videoUri != null) {
            val mediaItem = MediaItem.fromUri(videoUri)
            player.setMediaItem(mediaItem)
            player.prepare()
            player.playWhenReady = false
        }
    }

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {

        Text("Выбранное видео:", modifier = Modifier.padding(bottom = 16.dp))

        // Correct AndroidView usage
        AndroidView(
            modifier = Modifier
                .fillMaxWidth()
                .height(220.dp),
            factory = { ctx ->
                PlayerView(ctx).apply {
                    this.player = player
                }
            }
        )

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = {
                val intent = Intent(context, AnalysisResultActivity::class.java)
                intent.putExtra("video_uri", videoUri.toString())
                context.startActivity(intent)
            }
        ) {
            Text("Анализировать видео")
        }
    }
}
