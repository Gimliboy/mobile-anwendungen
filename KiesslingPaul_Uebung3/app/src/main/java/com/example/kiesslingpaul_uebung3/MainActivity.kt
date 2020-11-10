package com.example.kiesslingpaul_uebung3

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.list_item.view.*
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    private val item_list = generateList(15)
    private val adapter_value = MyAdapter(item_list)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recycler_list.adapter = adapter_value
        recycler_list.layoutManager = LinearLayoutManager(this)
    }

    fun startSecondActivity(view: View) {
        val i = Intent(this, secondActivity::class.java);
        startActivity(i);
    }

    private fun generateList(size: Int): ArrayList<StarWarsCharacter>{
        val tmp_item_list = ArrayList<StarWarsCharacter>()

        for (i in 0 until size){
            val tmpDrawable = when (i % 6){
                0 -> R.drawable.baby_yoda
                1 -> R.drawable.darth_sidious
                2 -> R.drawable.anakin_skywalker
                3 -> R.drawable.darth_vader
                4 -> R.drawable.han_solo
                else -> R.drawable.jar_jar

            }
            val tmpHeading = when (i % 6){
                0 -> "Baby Yoda"
                1 -> "Imparator"
                2 -> "Anakin Skywalker"
                3 -> "Darth Vader"
                4 -> "Han Solo"
                else -> "Jar Jar Binks"
            }
            val tmpDescription = when (i % 6){
                0 -> "little and cute"
                1 -> "very toxic"
                2 -> "easy to manipulate"
                3 -> "dad killer"
                4 -> "problems with son"
                else -> "funky and not much liked"
            }
            val item = StarWarsCharacter(tmpDrawable, tmpHeading, tmpDescription)
            tmp_item_list += item
        }

        return tmp_item_list
    }

    fun addItem(view: View){
        // add item at random position between 0 and 9 -> size must be >= 10
        val idx: Int = Random.nextInt(10)

        val randomInt = Random.nextInt(6)
        val tmpDrawable = when (randomInt){
            0 -> R.drawable.baby_yoda
            1 -> R.drawable.darth_sidious
            2 -> R.drawable.anakin_skywalker
            3 -> R.drawable.darth_vader
            4 -> R.drawable.han_solo
            else -> R.drawable.jar_jar

        }
        val tmpHeading = when (randomInt){
            0 -> "Baby Yoda"
            1 -> "Imparator"
            2 -> "Anakin Skywalker"
            3 -> "Darth Vader"
            4 -> "Han Solo"
            else -> "Jar Jar Binks"
        }
        val tmpDescription = when (randomInt){
            0 -> "little and cute"
            1 -> "very toxic"
            2 -> "easy to manipulate"
            3 -> "dad killer"
            4 -> "problems with son"
            else -> "funky and not much liked"
        }
        val newItem = StarWarsCharacter(tmpDrawable, tmpHeading, tmpDescription)

        item_list.add(idx, newItem)
        adapter_value.notifyItemInserted(idx)
    }
}

data class StarWarsCharacter(val imgSrc: Int, val characterName: String, val characterDesc: String)

class MyAdapter(private val data_list: List<StarWarsCharacter>) : RecyclerView.Adapter<MyAdapter.MyViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
        return MyViewHolder(itemView)
    }

    override fun getItemCount() = data_list.size

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = data_list[position]
        holder.imageView.setImageResource(currentItem.imgSrc)
        holder.textViewHeading.text = currentItem.characterName
        holder.textViewDescription.text = currentItem.characterDesc

    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView: ImageView = itemView.image_view
        val textViewHeading: TextView = itemView.text_view_heading
        val textViewDescription: TextView = itemView.text_view_description
    }
}