package com.jench.vanek.spotify

import com.jench.vanek.BuildConfig

import android.content.Context
import android.util.Log
import com.spotify.android.appremote.api.ConnectionParams
import com.spotify.android.appremote.api.Connector
import com.spotify.android.appremote.api.SpotifyAppRemote

class SpotifyPlayer(private val context: Context) {
    
    companion object {
        private const val TAG = "SpotifyPlayer"
        private const val CLIENT_ID = BuildConfig.SPOTIFY_CLIENT_ID
        private const val REDIRECT_URI = "vanek://callback"
    }

    private var spotifyAppRemote: SpotifyAppRemote? = null

    val isConnected: Boolean get() = spotifyAppRemote?.isConnected == true

    fun connect(onReady: () -> Unit = {}, onError: (Throwable) -> Unit = {}) {
        if (isConnected) { 
            onReady()
            return
        }

        val params = ConnectionParams.Builder(CLIENT_ID)
            .setRedirectUri(REDIRECT_URI)
            .showAuthView(true)
            .build()

        SpotifyAppRemote.connect(context, params, object : Connector.ConnectionListener {
            override fun onConnected(appRemote: SpotifyAppRemote) {
                spotifyAppRemote = appRemote
                Log.d(TAG, "Connected to Spotify")
                onReady()
            }

            override fun onFailure(throwable: Throwable) {
                Log.e(TAG, "Failed to connect to Spotify", throwable)
                onError(throwable)
            }
        })
    }

    fun play(uri: String) { spotifyAppRemote?.playerApi?.play(uri) }
    fun pause() { spotifyAppRemote?.playerApi?.pause() }
    fun resume() { spotifyAppRemote?.playerApi?.resume() } 
    fun skipNext() { spotifyAppRemote?.playerApi?.skipNext() }
    fun setShuffle(enabled: Boolean) { spotifyAppRemote?.playerApi?.setShuffle(enabled) }

    fun disconnect() {
        spotifyAppRemote?.let {
            SpotifyAppRemote.disconnect(it)
            spotifyAppRemote = null
            Log.d(TAG, "Disconnected from Spotify")
        }
    }
}