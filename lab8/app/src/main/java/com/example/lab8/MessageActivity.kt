package com.example.lab8

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import android.net.Uri
import android.widget.Button
import android.widget.TextView
//import com.example.lab8.databinding.ActivityMessageBinding



class MessageActivity : AppCompatActivity() {
    //private lateinit var binding: ActivityMessageBinding
    lateinit var messageLabel : TextView
    lateinit var  webButton: Button
    //private lateinit var binding: ActivityMessageBinding



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //binding = ActivityMessageBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_message)
        messageLabel = findViewById(R.id.message)
        //setSupportActionBar(binding.toolbar)
        messageLabel.text = ""
        webButton = findViewById(R.id.goToURL)

        //intent data
        val n = intent.getStringExtra("userName")
        val v = intent.getStringExtra("userVerb")
        val a = intent.getStringExtra("userAdj")

        messageLabel.text = "$n likes to $v with their $a hippo."

        webButton.setOnClickListener { view -> loadWebSite() }
    }

    override fun onBackPressed() {
        val data = Intent()
        data.putExtra("message", messageLabel.text.toString())
        setResult(Activity.RESULT_OK, data) //must be set before super.onBackPressed()
        super.onBackPressed()
        finish()
    }

    private fun loadWebSite(){
        //create intent
        val intent = Intent()
        val url = "https://www.madtakes.com/"
        intent.action = Intent.ACTION_VIEW
        intent.data = url.let{ Uri.parse(url)}

        // start activity
        startActivity(intent)
    }
}