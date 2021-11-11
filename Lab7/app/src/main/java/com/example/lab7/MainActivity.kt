package com.example.lab7

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.widget.*
import androidx.constraintlayout.widget.ConstraintLayout
import com.google.android.material.snackbar.Snackbar

@SuppressLint("UseSwitchCompatOrMaterialCode")

class MainActivity : AppCompatActivity() {
    var message = ""
    lateinit var radioGroup : RadioGroup
    lateinit var messageText :TextView
    lateinit var spinner : Spinner
    lateinit var american : CheckBox
    lateinit var italian :CheckBox
    lateinit var otherCuisine : CheckBox
    lateinit var switch : Switch
    lateinit var layout :ConstraintLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //get lateinit vars
        radioGroup = findViewById<RadioGroup>(R.id.radioGroup)
        messageText = findViewById<TextView>(R.id.message)
        spinner = findViewById<Spinner>(R.id.spinner)
        american = findViewById<CheckBox>(R.id.american)
        italian = findViewById<CheckBox>(R.id.italian)
        otherCuisine = findViewById<CheckBox>(R.id.other)
        switch = findViewById<Switch>(R.id.switch2)
        layout = findViewById<ConstraintLayout>(R.id.rootLayout)
    }

    fun places() {
        val list : MutableList<String> = mutableListOf<String>()
        val payment = radioGroup.checkedRadioButtonId
        if (payment == -1) {
            val fillingSnackBar = Snackbar.make(layout, "Please select a payment method", Snackbar.LENGTH_SHORT)
            fillingSnackBar.show()
        }else{
            if(spinner.selectedItem == "WillVill"){
               if(switch.isChecked){
                   if(american.isChecked){
                       list.add("Village Market")
                   }
                   if(italian.isChecked){
                       list.add("Village Market")
                   }
                   if(otherCuisine.isChecked){
                       list.add("Village Market")
                   }
               }else{
                   if(american.isChecked){
                       if(findViewById<RadioButton>(payment).text == "Other"){
                           list.add("The Grotto")
                           list.add("Village Market")
                           list.add("Village Center")
                       }else{
                           list.add("Village Market")
                           list.add("Village Center")
                       }
                   }
                   if(italian.isChecked){
                       list.add("Village Market")
                       list.add("Village Center")
                   }
                   if(otherCuisine.isChecked){
                       if(findViewById<RadioButton>(payment).text == "Other"){
                           list.add("The Grotto")
                           list.add("Village Market")
                           list.add("Village Center")
                       }else{
                           list.add("Village Market")
                           list.add("Village Center")
                       }
                   }
               }
            }else if(spinner.selectedItem == "Main Campus"){
                if(switch.isChecked){
                    if(american.isChecked){
                        if(findViewById<RadioButton>(payment).text == "Other"){
                            list += "UMC"
                            list += "Subway"
                            list += "Farrand"
                            list += "Farrand Market"
                        }else{
                            list += "UMC"
                            list += "Farrand"
                            list += "Farrand Market"
                        }
                    }
                    if(italian.isChecked){
                        if(findViewById<RadioButton>(payment).text == "Other"){
                            list += "UMC"
                            list += "Subway"
                        }else{
                            list += "UMC"
                        }
                    }
                    if(otherCuisine.isChecked){
                        if(findViewById<RadioButton>(payment).text == "Other"){
                            list += "UMC"
                            list += "Panda Express"
                        }else{
                            list += "UMC"
                        }
                    }
                }else{
                    if(american.isChecked){
                        if(findViewById<RadioButton>(payment).text == "Other"){
                            list += "C4C"
                            list += "UMC"
                            list += "WeatherTech"
                            list += "Sewell"
                            list += "Farrand"
                            list += "Farrand Market"
                            list += "Subway"
                        }else{
                            list += "C4C"
                            list += "UMC"
                            list += "Sewell"
                            list += "Farrand"
                            list += "Farrand Market"
                        }
                    }
                    if(italian.isChecked){
                        if(findViewById<RadioButton>(payment).text == "Other"){
                            list += "C4C"
                            list += "UMC"
                            list += "WeatherTech"
                            list += "Sewell"
                            list += "Subway"
                        }else{
                            list += "C4C"
                            list += "UMC"
                            list += "Sewell"
                        }
                    }
                    if(otherCuisine.isChecked){
                        if(findViewById<RadioButton>(payment).text == "Other"){
                            list += "C4C"
                            list += "UMC"
                            list += "WeatherTech"
                            list += "Sewell"
                            list += "Panda Express"
                        }else{
                            list += "C4C"
                            list += "UMC"
                            list += "Sewell"
                        }
                    }
                }
            }else if(spinner.selectedItem == "East Campus"){
                if(american.isChecked){
                    list += "SEEC"
                }
            }
        }
        val distinctPlaces = list.distinct()
        var placesText = ""
        if(distinctPlaces.isEmpty()){
            message = "Sorry there is nowhere that fits your choices"
            messageText.text = message
        }else {
            for (place in distinctPlaces){
                placesText += place + "\n"
            }
            message = "List of Places to eat: \n $placesText"
            messageText.text = message
        }
    }
    fun updateUI(){
        //TextView
        messageText.text = message
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString("message", message)
        super.onSaveInstanceState(outState)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        message = savedInstanceState.getString("message", "")
        updateUI()
    }
}