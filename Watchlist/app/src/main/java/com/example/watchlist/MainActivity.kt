package com.example.watchlist

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.room.Room
import com.example.watchlist.databinding.ActivityMainBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    lateinit var listV:ListView
    lateinit var pickMovieButton: Button
    lateinit var adapter: ArrayAdapter<Movie>
    lateinit var db: MovieRoomDatabase
    lateinit var curMovies: List<Movie>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.fab.setOnClickListener { view -> addMovie() }
        //create listview with items
        db = Room.databaseBuilder(
            applicationContext,
            MovieRoomDatabase::class.java, "database-name"
        ).allowMainThreadQueries().build()
        curMovies = db.itemDAO().getItems()
        listV = findViewById(R.id.list)
        adapter = myAdapter(this, curMovies)
        listV.adapter = adapter
        //set item on click listener
        listV.onItemClickListener =
            AdapterView.OnItemClickListener { parent, view, position, id ->
                val movieSelected = curMovies[position]
                //add intent to edit movie
                val intent = Intent(this, EditMovie::class.java)
                intent.putExtra("title", movieSelected.title)
                intent.putExtra("service", movieSelected.service)
                intent.putExtra("rating", movieSelected.rating)
                intent.putExtra("seen", movieSelected.seen)
                intent.putExtra("position", movieSelected.uid)
                startActivityForResult(intent, 2)
            }
        //setting onclick listener for the pick movie button
        pickMovieButton = findViewById(R.id.pick)
        pickMovieButton.setOnClickListener {
            if (curMovies.isEmpty()) {
                MaterialAlertDialogBuilder(this)
                    .setTitle("Alert")
                    .setMessage("No movies to pick from. Please add a movie")
                    .setNeutralButton("Ok") { dialog, which -> }
                    .show()
            }else{
                val selectedMovie = curMovies[Random.nextInt(until = curMovies.size)]
                MaterialAlertDialogBuilder(this)
                    .setTitle("Movie Picked!")
                    .setMessage("You should watch ${selectedMovie.title} on ${selectedMovie.service}")
                    .setNeutralButton("Ok!") { dialog, which -> }
                    .show()
            }
        }
    }

    private fun addMovie(){
        //create intent
        val intent = Intent(this, AddMovieActivity::class.java)
        startActivityForResult(intent, 1)
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if((requestCode == 1) && (resultCode == Activity.RESULT_OK)) { //add movie return
            val t = data?.getStringExtra("title").toString()
            val st = data?.getStringExtra("service").toString()
            val r = data?.getIntExtra("rating", 1)
            val se = data?.getBooleanExtra("watched", false)
            db.itemDAO().insert(Movie(0, t, st, r, se))
            curMovies = db.itemDAO().getItems()
            adapter = myAdapter(this, curMovies)
            adapter.notifyDataSetChanged()
            listV.adapter = adapter
        }
        else if((requestCode == 2) && (resultCode == Activity.RESULT_OK)){
            //edit movie

            val del = data?.getBooleanExtra("Delete", false)
            var pos = data?.getIntExtra("pos", 1)
            val t = data?.getStringExtra("title")
            val st = data?.getStringExtra("service")
            val r = data?.getIntExtra("rating", 1)
            val se = data?.getBooleanExtra("watched", false)
            val curMovie = Movie(pos!!, t!!, st!!, r, se)
            if(del  == false){//update
                db.itemDAO().update(curMovie)
                curMovies = db.itemDAO().getItems()
                adapter = myAdapter(this, curMovies)
                adapter.notifyDataSetChanged()
                listV.adapter = adapter
            }else if(del == true){//delete
                db.itemDAO().delete(curMovie)
                curMovies = db.itemDAO().getItems()
                adapter = myAdapter(this, curMovies)
                adapter.notifyDataSetChanged()
                listV.adapter = adapter
            }
        }
    }
}