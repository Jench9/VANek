package com.jench.vanek.spotify

object PlaylistRepository {
    private val playlists = mapOf(
        1 to "spotify:playlist:37i9dQZF1E387M12lg8iFn?si=61100123147c41a9", //Mix Diario 1
        2 to "spotify:playlist:1ADoB9uybY0YxvlVKR2UZI?si=22f2345879ed4589", //Haepy
        3 to "spotify:playlist:11fbaAXY0xvYWSKdUdDzmA?si=17b7af9652874b05", //Mi lista 1
        4 to "spotify:playlist:7sATe1aLBtZatV7xsNEtD5?si=1aa0516a41714a67", //MM

    )
    fun uriFor(number: Int): String? = playlists[number]
}
