package com.example.kiesslingpaul_uebung05

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val personRepo = PersonRepo()
        personRepo.getPerson(object: Callback<Person>{
            override fun onFailure(call: Call<Person>, t: Throwable) {
                //Toast.makeText(this, "something went wrong with the API Call", Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(call: Call<Person>, response: Response<Person>) {
                if(response.isSuccessful)
                {
                    val randomPerson = response.body()
                    if (randomPerson != null)
                        Log.i("TAG", "random Person: " + randomPerson.gender)
                    else
                        Log.WARN
                }
                else
                {
                    Log.ERROR
                }
            }
        })
    }
}

class PersonRepo(){
    val personAPI: PersonAPI;
    init {
        val gson = Gson()
        val retrofit = Retrofit.Builder()
        .baseUrl("https://randomuser.me/api")
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()

        personAPI = retrofit.create(PersonAPI::class.java)
    }
    fun getPerson(callback: Callback<Person>){
        val call: Call<Person> = personAPI.getPerson()
        call.enqueue(callback)
    }
}
