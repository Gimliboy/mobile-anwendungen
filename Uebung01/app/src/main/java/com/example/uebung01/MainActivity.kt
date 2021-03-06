package com.example.uebung01

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import java.time.LocalDateTime

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main);

        // load Data if existent
        loadData()

        // Click Listener
        left_Button.setOnClickListener {
            saveDate(left_Button.text.toString());
        }
        right_button.setOnClickListener {
            saveDate(right_button.text.toString());
        }
    }
    private fun loadData()
    {
        val sharedPreferences = getSharedPreferences("sharedPrefs", Context.MODE_PRIVATE);
        val preString = sharedPreferences.getString("CLICKED_BUTTON", null);
        // get popup msg in system language
        val popupMsgClicked: String = getString(R.string.popup_msg);
        val popUpMsgNotClicked: String = getString(R.string.popup_msg_not_clicked);
        if (preString != null)
            Toast.makeText(this, "$popupMsgClicked $preString" , Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(this, popUpMsgNotClicked, Toast.LENGTH_SHORT).show();
    }
    private fun saveDate(buttonText: String)
    {
        val sharedPreferences = getSharedPreferences("sharedPrefs", Context.MODE_PRIVATE);
        val editor = sharedPreferences.edit();
        Toast.makeText(this@MainActivity, buttonText, Toast.LENGTH_SHORT).show();
        editor.apply{
            putString("CLICKED_BUTTON", buttonText);
        }.apply();
    }
}