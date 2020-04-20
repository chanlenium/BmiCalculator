package com.example.bmicalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_result.*
import org.jetbrains.anko.toast

class ResultActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        /* Declare Int? type variable height & weight to receive nullable value
           Extract data from Intent                                            */
        val height: Int? = intent.getStringExtra("height").toInt()  // recieve intent value which corresponds to key value "height" and change type to integer
        if(height == null){ // if the intent value is null, finish program
            finish()
            return;
        }

        val weight: Int? = intent.getStringExtra("weight").toInt()  // recieve intent value which corresponds to key value "weight" and change type to integer
        if(weight == null){
            finish()
            return;
        }

        // Calculate BMI
        val bmi = weight / Math.pow(height / 100.0, 2.0)
        //Toast.makeText(this, "$bmi", Toast.LENGTH_SHORT).show() // toast message
        toast("$bmi")

        // Display results (Assign text to ID:resultTextView in activity_resutl.xml according to bmi value)
        when{
            bmi >= 35 -> resultTextView.text = "Severe obesity"
            bmi >= 30 -> resultTextView.text = "2nd step obesity"
            bmi >= 25 -> resultTextView.text = "1st step obesity"
            bmi >= 23 -> resultTextView.text = "Overweight"
            bmi >= 18.5 -> resultTextView.text = "Normal weight"
            else -> resultTextView.text = "Underweight"
        }

        // Display images at activity_resutl.xml according to bmi value
        // Resource location : res >> drawable >> ic_sentiment...
        when{
            bmi >= 23 ->
                imageView.setImageResource(
                    R.drawable.ic_sentiment_very_dissatisfied_black_24dp)
            bmi >= 18.5 ->
                imageView.setImageResource(
                    R.drawable.ic_sentiment_satisfied_black_24dp)
            else ->
                imageView.setImageResource(
                    R.drawable.ic_sentiment_dissatisfied_black_24dp)
        }
    }
}
