package com.example.ironreignpro.ui

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import com.example.ironreignpro.R

/**
 * DashboardFragment displays the overview cards for weekly tonnage, volume,
 * today's workout and personal record badges.  The layout is defined in
 * fragment_dashboard.xml.  No complex logic is required here yet.
 */
class DashboardFragment : Fragment(R.layout.fragment_dashboard) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d("DashboardFragment", "Dashboard created")

        // Configure bar charts for weekly tonnage and volume.  These use the MPAndroidChart
        // library to visualise progress instead of static text.  Dummy data is supplied
        // here; in a real implementation this would come from the ViewModel/Repository.
        val tonnageChart = view.findViewById<com.github.mikephil.charting.charts.BarChart>(R.id.chart_tonnage)
        val volumeChart = view.findViewById<com.github.mikephil.charting.charts.BarChart>(R.id.chart_volume)

        setupBarChart(tonnageChart, listOf(74.320f, 68.5f, 72.0f, 76.0f, 70.0f, 78.0f, 74.0f))
        setupBarChart(volumeChart, listOf(56f, 48f, 53f, 50f, 60f, 55f, 58f))
    }

    /**
     * Helper to initialise a BarChart with a list of seven float values representing the
     * training metric for each weekday.  The chart is styled with the primary colour
     * and simplified axes.
     */
    private fun setupBarChart(chart: com.github.mikephil.charting.charts.BarChart, values: List<Float>) {
        val entries = values.mapIndexed { index, value ->
            com.github.mikephil.charting.data.BarEntry(index.toFloat(), value)
        }
        val dataSet = com.github.mikephil.charting.data.BarDataSet(entries, "").apply {
            // Use the primary colour for the bars
            color = resources.getColor(R.color.color_primary, null)
            setDrawValues(false)
        }
        val data = com.github.mikephil.charting.data.BarData(dataSet)
        chart.data = data
        chart.setFitBars(true)
        chart.description.isEnabled = false
        chart.legend.isEnabled = false
        // Configure x-axis labels for days of the week
        val xAxis = chart.xAxis
        xAxis.valueFormatter = com.github.mikephil.charting.formatter.IndexAxisValueFormatter(
            listOf("Mo", "Di", "Mi", "Do", "Fr", "Sa", "So")
        )
        xAxis.granularity = 1f
        xAxis.setDrawGridLines(false)
        xAxis.setDrawAxisLine(false)
        xAxis.position = com.github.mikephil.charting.components.XAxis.XAxisPosition.BOTTOM
        // Configure y-axis to start at zero
        chart.axisLeft.axisMinimum = 0f
        chart.axisRight.isEnabled = false
        chart.setScaleEnabled(false)
        chart.setTouchEnabled(false)
        chart.invalidate()
    }
}