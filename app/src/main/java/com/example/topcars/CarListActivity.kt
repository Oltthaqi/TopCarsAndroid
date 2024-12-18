package com.example.topcars

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import android.widget.Toast
import androidx.activity.ComponentActivity
import com.example.topcars.adapters.CarAdapter
import com.example.topcars.models.Car

class CarListActivity : ComponentActivity() {
    private lateinit var carAdapter: CarAdapter
    private val favoriteCars = mutableListOf<Car>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.cars_list_layout)

        val carList = findViewById<ListView>(R.id.car_list)
        val searchInput = findViewById<EditText>(R.id.search_input)
        val searchButton = findViewById<Button>(R.id.search_button)
        val showFavoritesButton = findViewById<Button>(R.id.favorites_button)

        // List of cars
        val cars = mutableListOf(
            Car(R.drawable.bmw_m5, "Bmw", "M5", 300),
            Car(R.drawable.ferrari_italia, "Ferrari", "Italia", 250),
            Car(R.drawable.audi_r8, "Audi", "R8", 320),
            Car(R.drawable.mercedes_amg_gt, "Mercedes", "AMG GT", 340),
            Car(R.drawable.porsche_911, "Porsche", "911", 330),
            Car(R.drawable.lamborghini_huracan, "Lamborghini", "Huracan", 325),
            Car(R.drawable.bugatti_chiron, "Bugatti", "Chiron", 420),
            Car(R.drawable.chevrolet_corvette, "Chevrolet", "Corvette", 310),
            Car(R.drawable.ford_mustang, "Ford", "Mustang", 290),
            Car(R.drawable.nissan_gt_r, "Nissan", "GT-R", 315),
            Car(R.drawable.mclaren_720s, "McLaren", "720S", 355),
            Car(R.drawable.aston_martin_vantage, "Aston Martin", "Vantage", 290),
            Car(R.drawable.rolls_royce_phantom, "Rolls Royce", "Phantom", 250),
            Car(R.drawable.tesla_model_s, "Tesla", "Model S Plaid", 322),
            Car(R.drawable.bmw_i8, "BMW", "i8", 250),
            Car(R.drawable.jaguar_f_type, "Jaguar", "F-Type", 280),
            Car(R.drawable.alfa_romeo_4c, "Alfa Romeo", "4C Spider", 260),
            Car(R.drawable.mazda_rx_7, "Mazda", "RX-7", 240),
            Car(R.drawable.toyota_supra, "Toyota", "Supra", 250),
            Car(R.drawable.koenigsegg_agera, "Koenigsegg", "Agera RS", 447)
        )


        // Initialize adapter
        carAdapter = CarAdapter(this, cars, favoriteCars)
        carList.adapter = carAdapter

        // Handle search functionality
        searchButton.setOnClickListener {
            val query = searchInput.text.toString().trim()
            if (query.isNotEmpty()) {
                val filteredCars = cars.filter { it.carName.contains(query, ignoreCase = true) }
                if (filteredCars.isEmpty()) {
                    Toast.makeText(this, "No cars found for \"$query\"", Toast.LENGTH_SHORT).show()
                }
                carAdapter.updateData(filteredCars)
            } else {
                carAdapter.updateData(cars)
            }
        }

        // Show favorite cars
        showFavoritesButton.setOnClickListener {
            if (favoriteCars.isNotEmpty()) {
                carAdapter.updateData(favoriteCars)
            } else {
                Toast.makeText(this, "No favorite cars to show.", Toast.LENGTH_SHORT).show()
            }
        }

        // Handle list item click
        carList.setOnItemClickListener { _, _, position, _ ->
            val selectedCar = carAdapter.getItem(position) as Car
            Toast.makeText(this, "${selectedCar.carName} is clicked", Toast.LENGTH_LONG).show()

            val intent = Intent(this, DetailActivity::class.java).apply {
                putExtra("photo", selectedCar.photo)
                putExtra("name", selectedCar.carName)
                putExtra("model", selectedCar.carModel)
                putExtra("horsepower", selectedCar.horsepower)
            }
            startActivity(intent)
        }
    }
}
