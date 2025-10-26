package com.example.ironreignfull.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.ironreignfull.R

/**
 * MenuFragment shows quick actions and a list of all navigation options in a
 * vertically scrollable layout.  Clicking on a navigation item triggers a
 * navigation event to the corresponding destination defined in the nav graph.
 */
class MenuFragment : Fragment(R.layout.fragment_menu) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // Setup quick actions
        val logWorkout = view.findViewById<LinearLayout>(R.id.qa_log_workout)
        val addExercise = view.findViewById<LinearLayout>(R.id.qa_add_exercise)
        val recordWeight = view.findViewById<LinearLayout>(R.id.qa_record_weight)

        logWorkout.setOnClickListener {
            // Navigate to TrainingsFragment when logging a workout
            findNavController().navigate(R.id.trainingsFragment)
        }
        addExercise.setOnClickListener {
            // Navigate to AddExerciseFragment
            findNavController().navigate(R.id.addExerciseFragment)
        }
        recordWeight.setOnClickListener {
            // Placeholder: Show a toast as recording weight has not been implemented
            Toast.makeText(requireContext(), "Gewicht eintragen kommt bald", Toast.LENGTH_SHORT).show()
        }

        // Set up navigation list
        val recyclerView = view.findViewById<RecyclerView>(R.id.recycler_navigation)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        val items = listOf(
            NavItem(R.drawable.ic_trainings, getString(R.string.nav_trainings), R.id.trainingsFragment),
            NavItem(R.drawable.ic_menu, getString(R.string.nav_exercises), null),
            NavItem(R.drawable.ic_nutrition, getString(R.string.nav_nutrition), R.id.nutritionFragment),
            NavItem(R.drawable.ic_progress, getString(R.string.nav_progress), R.id.progressFragment),
            NavItem(R.drawable.ic_settings, getString(R.string.nav_settings), R.id.settingsFragment),
            NavItem(R.drawable.ic_timer, getString(R.string.nav_timer), null),
            NavItem(R.drawable.ic_heatmap, getString(R.string.nav_heatmap), null),
            NavItem(R.drawable.ic_iron_dna, getString(R.string.nav_irondna), null)
        )
        recyclerView.adapter = NavItemAdapter(items) { item ->
            item.destinationId?.let { destId ->
                findNavController().navigate(destId)
            } ?: run {
                Toast.makeText(requireContext(), "${item.label} ist noch nicht verfügbar", Toast.LENGTH_SHORT).show()
            }
        }
    }

    /** Data holder for a navigation item. */
    data class NavItem(val iconRes: Int, val label: String, val destinationId: Int?)

    /** RecyclerView adapter for the navigation items. */
    private class NavItemAdapter(
        private val items: List<NavItem>,
        private val onItemClick: (NavItem) -> Unit
    ) : RecyclerView.Adapter<NavItemAdapter.NavItemViewHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NavItemViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_navigation, parent, false)
            return NavItemViewHolder(view)
        }

        override fun onBindViewHolder(holder: NavItemViewHolder, position: Int) {
            val item = items[position]
            holder.bind(item)
            holder.itemView.setOnClickListener { onItemClick(item) }
        }

        override fun getItemCount(): Int = items.size

        class NavItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            private val icon: ImageView = itemView.findViewById(R.id.image_nav_icon)
            private val label: TextView = itemView.findViewById(R.id.text_nav_label)
            fun bind(item: NavItem) {
                icon.setImageResource(item.iconRes)
                label.text = item.label
            }
        }
    }
}