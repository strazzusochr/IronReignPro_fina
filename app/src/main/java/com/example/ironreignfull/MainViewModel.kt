package com.example.ironreignfull

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
 * MainViewModel holds the UI-related data for the app.  It exposes lists of
 * workouts and navigation items via LiveData so that fragments can observe
 * changes.  For this sample implementation the data is static, but in a
 * complete application the ViewModel would communicate with Room and Retrofit
 * repositories.
 */
class MainViewModel(application: Application) : AndroidViewModel(application) {

    // Mutable backing field for workouts
    private val _workouts = MutableLiveData<List<Workout>>()
    /**
     * Publicly exposed LiveData of workouts.  Observe this from a fragment
     * to update the UI whenever the list changes.
     */
    val workouts: LiveData<List<Workout>> get() = _workouts

    init {
        loadInitialData()
    }

    /**
     * Loads a static list of workouts asynchronously.  In a real app this
     * would query a database or network.  Exceptions are caught and logged.
     */
    private fun loadInitialData() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val initial = listOf(
                    Workout(
                        title = getApplication<Application>().getString(R.string.workout_bench),
                        subtitle = getApplication<Application>().getString(R.string.tempo_label)
                    ),
                    Workout(
                        title = getApplication<Application>().getString(R.string.workout_flyes),
                        subtitle = getApplication<Application>().getString(R.string.tempo_label)
                    ),
                    Workout(
                        title = getApplication<Application>().getString(R.string.workout_overhead),
                        subtitle = getApplication<Application>().getString(R.string.tempo_label)
                    )
                )
                _workouts.postValue(initial)
            } catch (e: Exception) {
                // In case of unexpected errors, log the stack trace
                e.printStackTrace()
            }
        }
    }
}

/**
 * Simple data class representing a workout entry consisting of a title (e.g.
 * exercise name with sets/reps/weight) and a subtitle (tempo and RPE).
 */
data class Workout(val title: String, val subtitle: String)