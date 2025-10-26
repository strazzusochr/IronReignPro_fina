package com.example.ironreignpro.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.ironreignpro.MainViewModel
import com.example.ironreignpro.R
import com.example.ironreignpro.Workout

/**
 * TrainingsFragment displays a list of workouts for the current day.  The list
 * is supplied by MainViewModel and rendered using a RecyclerView.  A button
 * at the bottom allows the user to add additional sets or exercises.
 */
class TrainingsFragment : Fragment(R.layout.fragment_trainings) {

    private val viewModel: MainViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Setup RecyclerView
        val recyclerView = view.findViewById<RecyclerView>(R.id.recycler_workouts)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        val adapter = WorkoutAdapter(emptyList())
        recyclerView.adapter = adapter

        // Observe workouts from ViewModel
        viewModel.workouts.observe(viewLifecycleOwner) { list ->
            adapter.updateData(list)
        }

        // Setup add sets button
        val addButton = view.findViewById<Button>(R.id.button_add_sets)
        addButton.setOnClickListener {
            Toast.makeText(requireContext(), "Satz hinzufügen kommt bald", Toast.LENGTH_SHORT).show()
        }
    }

    /**
     * RecyclerView adapter for workouts.
     */
    private class WorkoutAdapter(private var items: List<Workout>) : RecyclerView.Adapter<WorkoutAdapter.WorkoutViewHolder>() {

        fun updateData(newItems: List<Workout>) {
            items = newItems
            notifyDataSetChanged()
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WorkoutViewHolder {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.item_workout, parent, false)
            return WorkoutViewHolder(view)
        }

        override fun onBindViewHolder(holder: WorkoutViewHolder, position: Int) {
            val workout = items[position]
            holder.bind(workout)
        }

        override fun getItemCount(): Int = items.size

        class WorkoutViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            private val title: TextView = itemView.findViewById(R.id.text_workout_title)
            private val subtitle: TextView = itemView.findViewById(R.id.text_workout_subtitle)
            fun bind(workout: Workout) {
                title.text = workout.title
                subtitle.text = workout.subtitle
            }
        }
    }
}