package com.example.kiesslingpaul_uebung04

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.service.autofill.TextValueSanitizer
import android.widget.TextView
import org.apache.commons.io.IOUtils
import java.io.InputStream
import java.nio.charset.Charset
import java.nio.charset.StandardCharsets

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val iStream = resources.openRawResource(R.raw.movies)
        val jsonText = IOUtils.toString(iStream, Charset.defaultCharset())
        findViewById<TextView>(R.id.json_test).text = jsonText;
    }
}