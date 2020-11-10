package com.example.kiesslingpaul_uebung2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Parcel
import android.os.Parcelable
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import org.w3c.dom.Text

class KPaul2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_k_paul2)

        // get the input_text from intent
        val evaluationPoints = intent.getStringExtra(EVALUATION_POINTS)
        // apply value to textView
        if (evaluationPoints == null || evaluationPoints == "")
        {
            val textView = findViewById<TextView>(R.id.feedback_points).apply {
                text = "keine Bewertung eingetragen"
            }
        }
        else
        {
            val textView = findViewById<TextView>(R.id.feedback_points).apply {
                text = evaluationPoints
            }
        }
        val parcEvaluator= intent.getParcelableExtra<Evaluator>(EVALUATOR_DATA)
        if (parcEvaluator === null)
        {
            findViewById<TextView>(R.id.evaluator_name).text = "kein name angegeben"
            findViewById<TextView>(R.id.evaluator_status).text = "kein status angegeben"
        }
        else
        {
            findViewById<TextView>(R.id.evaluator_name).apply {
                text = parcEvaluator.name
            }
            findViewById<TextView>(R.id.evaluator_status).apply {
                text = parcEvaluator.status
            }
        }

    }
}