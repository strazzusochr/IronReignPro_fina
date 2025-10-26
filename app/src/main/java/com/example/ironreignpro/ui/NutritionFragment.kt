package com.example.ironreignpro.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.ironreignpro.R

/**
 * NutritionFragment is a placeholder for the nutrition tracking feature.  In
 * the final app this fragment would display a log of meals and allow the
 * user to track macronutrients and calories.
 */
class NutritionFragment : Fragment(R.layout.fragment_nutrition) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // Here we could load nutrition data from a ViewModel; for now we set up static values
        val caloriesProgress = view.findViewById<android.widget.ProgressBar>(R.id.progress_calories)
        val proteinProgress = view.findViewById<android.widget.ProgressBar>(R.id.progress_protein)
        val carbsProgress = view.findViewById<android.widget.ProgressBar>(R.id.progress_carbs)
        val fatProgress = view.findViewById<android.widget.ProgressBar>(R.id.progress_fat)

        // Example dynamic update: set progress values relative to max; these could be bound to LiveData
        caloriesProgress.progress = 1800
        proteinProgress.progress = 140
        carbsProgress.progress = 220
        fatProgress.progress = 50

        // Floating action button click handler
        val fab = view.findViewById<com.google.android.material.floatingactionbutton.FloatingActionButton>(R.id.fab_add_meal)
        fab.setOnClickListener {
            android.widget.Toast.makeText(requireContext(), "Neues Lebensmittel hinzufügen kommt bald", android.widget.Toast.LENGTH_SHORT).show()
        }
    }
}