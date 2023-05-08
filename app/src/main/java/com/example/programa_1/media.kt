package com.example.programa_1

import android.app.Service
import android.content.Intent
import android.media.MediaPlayer
import android.os.IBinder

class media : Service() {

    private lateinit var mediaPlayer: MediaPlayer
    private var isPaused: Boolean = false
    private var isRunning: Boolean = false

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    override fun onCreate() {
        super.onCreate()
        mediaPlayer = MediaPlayer.create(this, R.raw.musica1)
        mediaPlayer.isLooping = true
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        if (!isRunning) {
            mediaPlayer.start()
            isRunning = true
        }
        return START_STICKY
    }

    override fun onDestroy() {
        super.onDestroy()
        mediaPlayer.stop()
        mediaPlayer.release()
        isRunning = false
    }

    fun pause() {
        if (mediaPlayer.isPlaying) {
            mediaPlayer.pause()
            isPaused = true
        }
    }

    fun resume() {
        if (isPaused) {
            mediaPlayer.start()
            isPaused = false
        }
    }

}
