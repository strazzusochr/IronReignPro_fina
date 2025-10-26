package com.example.ironreignpro.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.ironreignpro.R

/**
 * ProgressFragment is a placeholder for progress charts and analytics.
 */
class ProgressFragment : Fragment(R.layout.fragment_progress) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // Setup progress charts with dummy data
        val weekChart = view.findViewById<com.github.mikephil.charting.charts.LineChart>(R.id.chart_week_progress)
        val muscleChart = view.findViewById<com.github.mikephil.charting.charts.BarChart>(R.id.chart_muscle_group)
        setupWeekLineChart(weekChart)
        setupMuscleBarChart(muscleChart)

        // The "View Stats" button currently does nothing but could navigate to a detailed stats page.
        val button = view.findViewById<android.widget.Button>(R.id.button_view_stats)
        button.setOnClickListener {
            // Placeholder for future navigation or behaviour
            android.widget.Toast.makeText(requireContext(), "Statistiken werden bald verfügbar sein", android.widget.Toast.LENGTH_SHORT).show()
        }
    }

    /**
     * Creates a simple line chart for weekly data.  The x-axis represents days of the week
     * and the y-axis shows a performance metric (e.g. tonnage).  Data is hard coded for
     * demonstration; replace with ViewModel data in a real implementation.
     */
    private fun setupWeekLineChart(chart: com.github.mikephil.charting.charts.LineChart) {
        val entries = listOf(
            com.github.mikephil.charting.data.Entry(0f, 20f),
            com.github.mikephil.charting.data.Entry(1f, 25f),
            com.github.mikephil.charting.data.Entry(2f, 30f),
            com.github.mikephil.charting.data.Entry(3f, 40f),
            com.github.mikephil.charting.data.Entry(4f, 35f),
            com.github.mikephil.charting.data.Entry(5f, 28f),
            com.github.mikephil.charting.data.Entry(6f, 32f)
        )
        val dataSet = com.github.mikephil.charting.data.LineDataSet(entries, "").apply {
            color = resources.getColor(R.color.color_secondary, null)
            setDrawCircles(false)
            lineWidth = 2f
            setDrawValues(false)
        }
        chart.data = com.github.mikephil.charting.data.LineData(dataSet)
        chart.description.isEnabled = false
        chart.legend.isEnabled = false
        val xAxis = chart.xAxis
        xAxis.valueFormatter = com.github.mikephil.charting.formatter.IndexAxisValueFormatter(
            listOf("Mo", "Di", "Mi", "Do", "Fr", "Sa", "So")
        )
        xAxis.position = com.github.mikephil.charting.components.XAxis.XAxisPosition.BOTTOM
        xAxis.setDrawGridLines(false)
        xAxis.setDrawAxisLine(false)
        xAxis.granularity = 1f
        chart.axisLeft.axisMinimum = 0f
        chart.axisRight.isEnabled = false
        chart.setScaleEnabled(false)
        chart.setTouchEnabled(false)
        chart.invalidate()
    }

    /**
     * Creates a bar chart representing repetitions per muscle group.  Labels correspond
     * to muscle regions (e.g. back, biceps, legs) and values show total reps.  Data is
     * placeholder.
     */
    private fun setupMuscleBarChart(chart: com.github.mikephil.charting.charts.BarChart) {
        val entries = listOf(
            com.github.mikephil.charting.data.BarEntry(0f, 30f), // Back
            com.github.mikephil.charting.data.BarEntry(1f, 20f), // Biceps
            com.github.mikephil.charting.data.BarEntry(2f, 25f)  // Legs
        )
        val dataSet = com.github.mikephil.charting.data.BarDataSet(entries, "").apply {
            color = resources.getColor(R.color.color_secondary, null)
            setDrawValues(false)
        }
        chart.data = com.github.mikephil.charting.data.BarData(dataSet)
        chart.description.isEnabled = false
        chart.legend.isEnabled = false
        val xAxis = chart.xAxis
        xAxis.valueFormatter = com.github.mikephil.charting.formatter.IndexAxisValueFormatter(
            listOf("Rücken", "Bizeps", "Beine")
        )
        xAxis.position = com.github.mikephil.charting.components.XAxis.XAxisPosition.BOTTOM
        xAxis.setDrawGridLines(false)
        xAxis.setDrawAxisLine(false)
        xAxis.granularity = 1f
        chart.axisLeft.axisMinimum = 0f
        chart.axisRight.isEnabled = false
        chart.setScaleEnabled(false)
        chart.setTouchEnabled(false)
        chart.invalidate()
    }
}