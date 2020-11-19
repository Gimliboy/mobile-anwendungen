package com.example.kiesslingpaul_uebung05

import android.media.Image
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.google.gson.Gson
import com.squareup.picasso.Picasso
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.Exception

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    // clicking to get a random person without filter
        findViewById<Button>(R.id.btn_load_rnd_person).setOnClickListener{
            try{

                val personRepo = PersonRepo()
                personRepo.getPerson(object: Callback<Results>{
                    override fun onFailure(call: Call<Results>, t: Throwable) {
                        //Toast.makeText(this, "something went wrong with the API Call", Toast.LENGTH_SHORT).show()
                        Toast.makeText(this@MainActivity, "following error occured: " + t.message, Toast.LENGTH_LONG).show()
                    }

                    override fun onResponse(call: Call<Results>, response: Response<Results>) {
                        if(response.isSuccessful)
                        {
                            // store the response from the Call
                            val responseResults = response.body()
                            if (responseResults !== null && responseResults.results.count() > 0)
                            {
                                // store the Person -> only one person in the results-list
                                val randomPerson = responseResults.results[0]

                                // assign the content to the single text Views -> used formatted strings in every place for consistency
                                findViewById<TextView>(R.id.full_name_text_view).text = getString(R.string.full_name_string, randomPerson.name.title, randomPerson.name.first, randomPerson.name.last)
                                findViewById<TextView>(R.id.location_street_text_view).text = getString(R.string.location_street_string, randomPerson.location.street.number, randomPerson.location.street.name)
                                findViewById<TextView>(R.id.location_city_text_view).text = getString(R.string.location_city_string, randomPerson.location.postcode, randomPerson.location.city)
                                findViewById<TextView>(R.id.location_state_text_view).text = getString(R.string.location_state_string, randomPerson.location.state)
                                findViewById<TextView>(R.id.location_country_text_view).text = getString(R.string.location_country_string, randomPerson.location.country)
                                val pictureView = findViewById<ImageView>(R.id.picture_view)
                                Picasso.get().load(randomPerson.picture.large).into(pictureView)
                            }
                            else
                            {
                                Toast.makeText(this@MainActivity, "no results came in from the API - please reload the App", Toast.LENGTH_LONG).show()
                            }
                        }
                        else
                        {
                            Log.v("TAG", "response is not successful")
                        }
                    }
                })
            } catch (e: Exception){
                Toast.makeText(this@MainActivity, "Problem with calling the get() function", Toast.LENGTH_LONG).show()
            }
        }

    // clicking to get person with filter for 'gender = male'
        findViewById<Button>(R.id.btn_load_male_person).setOnClickListener{
                        try{
                            val personRepo = PersonRepo()
                personRepo.getMalePerson(object: Callback<Results>{
                    override fun onFailure(call: Call<Results>, t: Throwable) {
                        //Toast.makeText(this, "something went wrong with the API Call", Toast.LENGTH_SHORT).show()
                        Toast.makeText(this@MainActivity, "following error occured: " + t.message, Toast.LENGTH_LONG).show()
                    }

                    override fun onResponse(call: Call<Results>, response: Response<Results>) {
                        if(response.isSuccessful)
                        {
                            // store the response from the Call
                            val responseResults = response.body()
                            if (responseResults !== null && responseResults.results.count() > 0)
                            {
                                // store the Person -> only one person in the results-list
                                val randomPerson = responseResults.results[0]

                                // assign the content to the single text Views -> used formatted strings in every place for consistency
                                findViewById<TextView>(R.id.full_name_text_view).text = getString(R.string.full_name_string, randomPerson.name.title, randomPerson.name.first, randomPerson.name.last)
                                findViewById<TextView>(R.id.location_street_text_view).text = getString(R.string.location_street_string, randomPerson.location.street.number, randomPerson.location.street.name)
                                findViewById<TextView>(R.id.location_city_text_view).text = getString(R.string.location_city_string, randomPerson.location.postcode, randomPerson.location.city)
                                findViewById<TextView>(R.id.location_state_text_view).text = getString(R.string.location_state_string, randomPerson.location.state)
                                findViewById<TextView>(R.id.location_country_text_view).text = getString(R.string.location_country_string, randomPerson.location.country)
                                val pictureView = findViewById<ImageView>(R.id.picture_view)
                                Picasso.get().load(randomPerson.picture.large).into(pictureView)
                            }
                            else
                            {
                                Toast.makeText(this@MainActivity, "no results came in from the API - please reload the App", Toast.LENGTH_LONG).show()
                            }
                        }
                        else
                        {
                            Log.v("TAG", "response is not successful")
                        }
                    }
                })
            } catch (e: Exception){
                Toast.makeText(this@MainActivity, "Problem with calling the get() function", Toast.LENGTH_LONG).show()
            }
        }
    }
}


class PersonRepo(){
    private val personAPI: PersonAPI;
    init {
        val gson = Gson()
        val retrofit = Retrofit.Builder()
        .baseUrl("https://randomuser.me/api/")
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()
        personAPI = retrofit.create(PersonAPI::class.java)
    }
    fun getPerson(callback: Callback<Results>){
        val call: Call<Results> = personAPI.getPerson()
        call.enqueue(callback)
    }
    fun getMalePerson(callback: Callback<Results>){
        val call: Call<Results> = personAPI.getMalePerson()
        call.enqueue(callback)
    }
}
