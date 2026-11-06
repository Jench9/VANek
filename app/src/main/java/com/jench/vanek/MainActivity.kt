package com.jench.vanek

import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.jench.vanek.spotify.SpotifyPlayer

class MainActivity : AppCompatActivity() {

    private lateinit var player: SpotifyPlayer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        player = SpotifyPlayer(this)

        findViewById<Button>(R.id.playButton).setOnClickListener {
            player.connect(
                onReady = { player.play("spotify:playlist:1ADoB9uybY0YxvlVKR2UZI?si=4c798a53e6c44ec3") },
                onError = { Log.e("MainActivity", "Spotify connect failed", it) }
            )
        }
        findViewById<Button>(R.id.pauseButton).setOnClickListener { player.pause() }
        findViewById<Button>(R.id.nextButton).setOnClickListener { player.skipNext() }
    }

    override fun onDestroy() {
        player.disconnect()
        super.onDestroy()
    }
}