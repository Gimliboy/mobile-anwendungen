package com.example.kiesslingpaul_uebung2

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Parcel
import android.os.Parcelable
import android.view.View
import android.widget.EditText
import android.widget.Toast
import kotlinx.android.parcel.Parcelize

// constant key to access value
const val EVALUATION_POINTS = "EVALUATION_POINTS_KEY"
const val EVALUATOR_DATA = "EVALUATOR_DATA_KEY"
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun sendEvaluation(view: View){
        // take the text from input and cast to String
        val inputTextFeedback = findViewById<EditText>(R.id.input_text)
        val inputString = inputTextFeedback.text.toString()
        val i = Intent(this, KPaul2::class.java).apply{
            // add data to intent
            putExtra(EVALUATION_POINTS, inputString)
            putExtra(EVALUATOR_DATA, Evaluator(findViewById<EditText>(R.id.input_name).text.toString(), findViewById<EditText>(R.id.input_status).text.toString()))
        }
        startActivity(i);
    }

    fun sendMail(view: View) {
        // create intent
        val intent = Intent(Intent.ACTION_SEND)
        intent.data = Uri.parse("mailto:")
        intent.type = "text/plain"
        // add data to intent - receptor, subject, content
        intent.putExtra(Intent.EXTRA_EMAIL, arrayOf("droidmule123@gmail.com"))
        intent.putExtra(Intent.EXTRA_SUBJECT, "Mobile Anwendungen 2020 – Uebung2")
        intent.putExtra(
            Intent.EXTRA_TEXT,
            "Abgeschickt von Uebung 2, welche sehr gut bewertet werden sollte"
        )

        // try to start the intent -> catch possible errors
        try {
            //start email intent
            startActivity(Intent.createChooser(intent, "Choose Email Client..."))
        }
        catch (e: Exception){
            // show error if anything goes wrong
            Toast.makeText(this, e.message, Toast.LENGTH_LONG).show()
        }

    }
}

@Parcelize
data class Evaluator(val name: String, val status: String) : Parcelable