package com.example.lab6

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
    fun checkAnimal(view: android.view.View) {
        val messageText = findViewById<TextView>(R.id.animalMessage)
        val animalName = findViewById<EditText>(R.id.animalText)
        val animal = animalName.text
        if(animal.toString().lowercase() == "red panda"){
            messageText.text = "You guessed right! The Red Panda should be your favorite"
        }else{
            messageText.text = "Your favorite animal should be the Red Panda! Not the " + animal
        }
        val img = findViewById<ImageView>(R.id.imageView)
        img.setImageResource(R.drawable.red_panda_ftr)
        img.visibility = View.VISIBLE
    }
}