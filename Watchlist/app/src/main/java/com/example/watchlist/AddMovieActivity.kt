package com.example.watchlist

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.Spinner
import android.widget.TextView
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController

class AddMovieActivity : AppCompatActivity() {
    lateinit var addMovieButton: Button
    lateinit var cancelButton: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_addmovie)
        addMovieButton = findViewById(R.id.edit)
        cancelButton = findViewById(R.id.delete)

        addMovieButton.setOnClickListener {addMovie()}
        cancelButton.setOnClickListener{
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }

    private fun addMovie(){
        //create intent
        val title: String = findViewById<TextView>(R.id.movieTitle).text.toString()
        val service: String = findViewById<TextView>(R.id.serviceText).text.toString()
        val rating: Int = findViewById<Spinner>(R.id.ratingSpinner).selectedItemPosition
        val watched: Boolean = findViewById<CheckBox>(R.id.seen).isChecked

        if(title.isEmpty()){
            //popup Pls add movie
            showAlertDialog("Please enter a movie title.")
        }else if(service.isEmpty()){
            //pop up pls add service
            showAlertDialog("Please enter a streaming service.")
        }else {
            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra("title",title)
            intent.putExtra("service",service)
            intent.putExtra("rating",rating + 1)
            intent.putExtra("watched",watched)
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