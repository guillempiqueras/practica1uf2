package guillem.piqueras.lopez

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<Button>(R.id.gravador).setOnClickListener {
            val intent = Intent(this, gravar::class.java)
            startActivity(intent)
        }
        findViewById<Button>(R.id.reproductor).setOnClickListener {
            val intent = Intent(this, reproducirvideo::class.java)
            startActivity(intent)
        }
        findViewById<Button>(R.id.camara).setOnClickListener {
            val intent = Intent(this, fotos::class.java)
            startActivity(intent)
        }
    }
}