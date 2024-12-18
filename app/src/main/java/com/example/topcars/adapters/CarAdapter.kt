package com.example.topcars.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.example.topcars.R
import com.example.topcars.models.Car

class CarAdapter(
    private val context: Context,
    private var carList: List<Car>,
    private val favoriteCars: MutableList<Car>
) : BaseAdapter() {

    private val layoutInflater: LayoutInflater =
        context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

    override fun getCount(): Int = carList.size

    override fun getItem(position: Int): Any = carList[position]

    override fun getItemId(position: Int): Long = position.toLong()

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val rowView: View = convertView ?: layoutInflater.inflate(R.layout.cars_item_layout, parent, false)

        // Initialize UI components
        val carPhoto = rowView.findViewById<ImageView>(R.id.item_image)
        val carName = rowView.findViewById<TextView>(R.id.item_name)
        val carModel = rowView.findViewById<TextView>(R.id.item_age)
        val favoriteButton = rowView.findViewById<ImageView>(R.id.deleteButton)

        val car = carList[position]

        // Bind data
        carPhoto.setImageResource(car.photo)
        carName.text = car.carName
        carModel.text = car.carModel

        // Update the button based on favorite status
        if (favoriteCars.contains(car)) {
            favoriteButton.setImageResource(R.drawable.ic_favorite_selected) // Replace with selected icon
        } else {
            favoriteButton.setImageResource(R.drawable.ic_favorite) // Replace with unselected icon
        }

        // Handle favorite button click
        favoriteButton.setOnClickListener {
            toggleFavorite(car)
        }

        return rowView
    }

    private fun toggleFavorite(car: Car) {
        if (favoriteCars.contains(car)) {
            favoriteCars.remove(car)
            Toast.makeText(context, "${car.carName} removed from favorites", Toast.LENGTH_SHORT).show()
        } else {
            favoriteCars.add(car)
            Toast.makeText(context, "${car.carName} added to favorites", Toast.LENGTH_SHORT).show()
        }
        notifyDataSetChanged() // Refresh the list
    }

    // Method to update the car list dynamically
    fun updateData(newCarList: List<Car>) {
        carList = newCarList
        notifyDataSetChanged()
    }
}
