package guillem.piqueras.lopez

import android.Manifest
import android.annotation.SuppressLint
import android.annotation.TargetApi
import android.content.pm.PackageManager
import android.media.MediaRecorder
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.widget.Button
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import java.io.IOException

class gravar : AppCompatActivity() {
    private lateinit var salida: String
    private var mediaRecorder: MediaRecorder? = null
    private var startet: Boolean = false
    private var stopet: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gravar)




        findViewById<Button>(R.id.btnStrart).setOnClickListener {
            if (ContextCompat.checkSelfPermission(this,
                    Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED && ContextCompat.checkSelfPermission(this,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                val permissions = arrayOf(android.Manifest.permission.RECORD_AUDIO, android.Manifest.permission.WRITE_EXTERNAL_STORAGE, android.Manifest.permission.READ_EXTERNAL_STORAGE)
                ActivityCompat.requestPermissions(this, permissions,0)
            }else{
                start()
            }
        }
        findViewById<Button>(R.id.btnStop).setOnClickListener {
            stop()
        }
        findViewById<Button>(R.id.btnDetener).setOnClickListener {
            detener()
        }


    }

    private fun detener() {
        if(startet){
            mediaRecorder?.stop()
            mediaRecorder?.release()
            startet = false
        }else{
            Toast.makeText(this, "la gravacion ya a terminado", Toast.LENGTH_SHORT).show()
        }
    }
    @SuppressLint("RestrictedApi", "SetTextI18n")
    @TargetApi(Build.VERSION_CODES.N)
    private fun stop() {
        if (startet) {
            if (!stopet) {
                Toast.makeText(this, "gravacion en pausa", Toast.LENGTH_SHORT).show()
                mediaRecorder?.pause()
                stopet = true
                findViewById<Button>(R.id.btnStop).text = "Resume"
            } else {
                resume()
            }
        }
    }

    private fun start() {

        salida = Environment.getExternalStorageDirectory().absolutePath + "/recording.mp3"
        mediaRecorder = MediaRecorder()
        mediaRecorder?.setAudioSource(MediaRecorder.AudioSource.MIC)
        mediaRecorder?.setOutputFormat(MediaRecorder.OutputFormat.MPEG_4)
        mediaRecorder?.setAudioEncoder(MediaRecorder.AudioEncoder.AAC)
        mediaRecorder?.setOutputFile(salida)

        try {
            mediaRecorder?.prepare()
            mediaRecorder?.start()
            startet = true
            Toast.makeText(this, "grabando", Toast.LENGTH_SHORT).show()
        } catch (e: IllegalStateException) {
            e.printStackTrace()
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    @SuppressLint("RestrictedApi", "SetTextI18n")
    @TargetApi(Build.VERSION_CODES.N)
    private fun resume() {
        Toast.makeText(this,"Resume", Toast.LENGTH_SHORT).show()
        mediaRecorder?.resume()
        findViewById<Button>(R.id.btnStop).text = "Pause"
        stopet = false
    }
}