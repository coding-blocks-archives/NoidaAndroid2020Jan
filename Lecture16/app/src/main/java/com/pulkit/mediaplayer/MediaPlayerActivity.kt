package com.pulkit.mediaplayer

import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.widget.MediaController
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_media_player.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.File

class MediaPlayerActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_media_player)

        videoView.setMediaController(MediaController(this))
//        val path = "android.resource://$packageName/${R.raw.video}"
//        videoView.setVideoURI(Uri.parse(path))

        copyFileFromAssets()

    }

    private fun copyFileFromAssets() {
        GlobalScope.launch {
            val file = assets.open("video.mp4")
            val dir = getExternalFilesDir(Environment.DIRECTORY_MOVIES)
            val videoFile = File(dir, "video.mp4")
            if (videoFile.exists()) {
                withContext(Dispatchers.Main) {
                    videoView.setVideoURI(Uri.parse(videoFile.absolutePath))
                    videoView.start()
                }
            } else
                withContext(Dispatchers.IO) { videoFile.writeBytes(file.readBytes()) }


        }
    }
}
