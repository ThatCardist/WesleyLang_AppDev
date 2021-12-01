package com.example.lab8

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
//import com.example.lab8.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    lateinit var nameTextBox : EditText
    lateinit var verbTextBox : EditText
    lateinit var adjTextBox : EditText
    lateinit var sentenceButton : Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        nameTextBox = findViewById(R.id.TextPersonName)
        verbTextBox = findViewById(R.id.editTextVerb)
        adjTextBox = findViewById(R.id.editTextTextAdj)
        sentenceButton = findViewById(R.id.makeSentence)

        //event listener
        sentenceButton.setOnClickListener {
            val name = nameTextBox.text.toString()
            val verb = verbTextBox.text.toString()
            val adj = adjTextBox.text.toString()


            //create intent
            val intent = Intent(this, MessageActivity::class.java)
            intent.putExtra("userName", name)
            intent.putExtra("userVerb", verb)
            intent.putExtra("userAdj", adj)

            startActivity(intent)
        }

    }
//    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
//        super.onActivityResult(requestCode, resultCode, data)
//        if((requestCode == REQUEST_CODE) && (resultCode == Activity.RESULT_OK)) {
//            //reviewTextView.setText(data?.let{data.getStringExtra("feedback")})
//
//        }
//    }
}