package com.example.ironreignfull.ui

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import com.example.ironreignfull.R

/**
 * DashboardFragment displays the overview cards for weekly tonnage, volume,
 * today's workout and personal record badges.  The layout is defined in
 * fragment_dashboard.xml.  No complex logic is required here yet.
 */
class DashboardFragment : Fragment(R.layout.fragment_dashboard) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d("DashboardFragment", "Dashboard created")
    }
}