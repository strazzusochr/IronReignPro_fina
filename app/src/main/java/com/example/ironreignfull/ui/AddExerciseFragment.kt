package com.example.ironreignfull.ui

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.ironreignfull.R

/**
 * AddExerciseFragment allows the user to add a new exercise.  It currently
 * displays static content mirroring the mockup and provides a stop button for
 * a placeholder timer.  Future implementations can hook this timer into
 * chronometer APIs.
 */
class AddExerciseFragment : Fragment(R.layout.fragment_add_exercise) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val stopButton: Button = view.findViewById(R.id.button_stop)
        stopButton.setOnClickListener {
            Toast.makeText(requireContext(), "Timer gestoppt", Toast.LENGTH_SHORT).show()
        }
    }
}