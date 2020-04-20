package com.example.bmicalculator

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.preference.PreferenceManager
import android.widget.Toast
import androidx.core.util.rangeTo
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // load previous input values
        loadData()

        // When push "result" button..
        resultButton.setOnClickListener{
            // Save input values before activate ResultActivity
            saveData(heightEditText.text.toString().toInt(), weightEditText.text.toString().toInt())

            // activate ResultActivity
            val intent = Intent(this, ResultActivity::class.java)   // generate intent instance
            intent.putExtra("weight", weightEditText.text.toString())   // Put data in intent "putExtra(Key, Value) expression"
            intent.putExtra("height", heightEditText.text.toString())
            startActivity(intent)   // call method "startActivity()" with argument intent
        }
    }

    /* SharedPreference: used to store simple data such as setting values */
    private fun saveData(height: Int, weight: Int){ // store data
        val pref = getSharedPreferences("pref", Context.MODE_PRIVATE)   // declare preference object
        val editor = pref.edit()    // get "edit object" of preference object

        editor.putInt("KEY_HEIGHT", height) // Store data (format: put[data type]("Key", value))
            .putInt("KEY_WEIGHT", weight)
            .apply()    // return the object (usually used when changing the state of an object, and return the object)
    }

    private fun loadData(){ // call data
        val pref = getSharedPreferences("pref", Context.MODE_PRIVATE)   // declare preference object
        val height = pref.getInt("KEY_HEIGHT", 0)   // get data (format: get[data type]("Key", value))
        val weight = pref.getInt("KEY_WEIGHT", 0)   // get default value "0" when the stored data does not exist

        if(height != 0 && weight != 0){ // if stored data exists, set the values to heightEditText and weightEditText
            heightEditText.setText(height.toString())
            weightEditText.setText(weight.toString())
        }
    }
}
