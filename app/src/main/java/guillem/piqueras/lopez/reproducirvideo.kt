package guillem.piqueras.lopez

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.MediaController
import android.widget.VideoView

class reproducirvideo : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reproducirvideo)

        var videoView = findViewById<VideoView>(R.id.videoView)
        videoView.setVideoURI(Uri.parse("android.resource://"+packageName+"/"+ R.raw.video1))

        var mediaController = MediaController(this)
        videoView.setMediaController(mediaController)
    }
}