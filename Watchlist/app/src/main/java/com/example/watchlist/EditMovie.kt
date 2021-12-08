package com.example.watchlist

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.CheckBox
import android.widget.Spinner
import android.widget.TextView
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class EditMovie : AppCompatActivity() {
    lateinit var deleteButton: Button
    lateinit var editButton: Button
    lateinit var title: TextView
    lateinit var service: TextView
    lateinit var rating: Spinner
    lateinit var seen: CheckBox
    var pos: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_movie)
        deleteButton = findViewById(R.id.delete)
        editButton = findViewById(R.id.edit)
        title = findViewById(R.id.movieTitle)
        service = findViewById(R.id.serviceText)
        rating = findViewById(R.id.ratingSpinner)
        seen = findViewById(R.id.seen)

        title.text = intent.getStringExtra("title")
        service.text = intent.getStringExtra("service")
        rating.setSelection(intent.getIntExtra("rating", 1) -1)
        seen.isChecked = intent.getBooleanExtra("seen", false)

        pos = intent.getIntExtra("position", -1)
        //deleteButton onclick listener
        deleteButton.setOnClickListener{
            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra("Delete", true)
            intent.putExtra("title", title.text.toString())
            intent.putExtra("service",service.text.toString())
            intent.putExtra("rating",findViewById<Spinner>(R.id.ratingSpinner).selectedItemPosition + 1)
            intent.putExtra("watched",seen.isChecked)
            intent.putExtra("pos", pos)
            setResult(Activity.RESULT_OK, intent)
            finish()
        }
        //editButton onclick event
        editButton.setOnClickListener{EditMovie()}
    }

    private fun EditMovie(){
        //create intent
        val title: String = title.text.toString()
        val service: String = service.text.toString()
        val rating: Int = findViewById<Spinner>(R.id.ratingSpinner).selectedItemPosition
        val watched: Boolean = seen.isChecked

        if(title.isEmpty()){
            //popup Pls add movie
            showAlertDialog("Please enter a movie title.")
        }else if(service.isEmpty()){
            //pop up pls add service
            showAlertDialog("Please enter a streaming service.")
        }else {
            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra("pos", pos)
            intent.putExtra("title",title)
            intent.putExtra("service",service)
            intent.putExtra("rating",rating + 1)
            intent.putExtra("watched",watched)
            intent.putExtra("Delete", false)
            setResult(Activity.RESULT_OK, intent)
            finish()
        }
    }
    private fun showAlertDialog(message: String){
        //popup code from this video https://www.youtube.com/watch?v=ptBW9tP2cHA
        MaterialAlertDialogBuilder(this)
            .setTitle("Alert")
            .setMessage(message)
            .setNeutralButton("Ok"){dialog, which -> }
            .show()
    }
}