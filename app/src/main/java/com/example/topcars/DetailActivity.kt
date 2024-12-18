package com.example.topcars

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.ComponentActivity
import androidx.appcompat.widget.AppCompatButton

class DetailActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.cars_details_activity)

        var image =findViewById<ImageView>(R.id.details_profile_image)
        var name = findViewById<TextView>(R.id.details_name)
        var model = findViewById<TextView>(R.id.details_car_model)
        var horespower = findViewById<TextView>(R.id.details_horse_power)

        image.setImageResource(intent.getIntExtra("photo", 0))
        name.text =intent.getStringExtra("name")
        model.text = intent.getStringExtra("model")
        horespower.text = intent.getIntExtra("horsepower", 0).toString()

        val goBackButton =findViewById<AppCompatButton>(R.id.backButton)
        goBackButton.setOnClickListener {
            finish()
        }
    }
}