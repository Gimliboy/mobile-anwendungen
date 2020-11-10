package com.example.kiesslingpaul_uebung3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast

class secondActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
    }
    fun displayInput(view: View)
    {
        val editText1 = findViewById<EditText>(R.id.first_text)
        val editText2 = findViewById<EditText>(R.id.second_text)
        val displayTheInput = getString(R.string.user_name, editText1.text, editText2.text)
        Toast.makeText(this, displayTheInput.toString(), Toast.LENGTH_LONG).show()
    }
}