package com.example.ironreignpro.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.ironreignpro.R

/**
 * SettingsFragment displays application preferences.  It currently displays
 * placeholder text.  In a fully implemented version this fragment would
 * manage theme, notifications and other configurable options.
 */
class SettingsFragment : Fragment(R.layout.fragment_settings) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // Setup switches and click listeners for settings.  When toggled, a short
        // message is shown; in a complete implementation these values would be saved
        // to a persistent store (e.g. DataStore).
        val switchArHeatmap = view.findViewById<com.google.android.material.switchmaterial.SwitchMaterial>(R.id.switch_ar_heatmap)
        val switchRemainingReps = view.findViewById<com.google.android.material.switchmaterial.SwitchMaterial>(R.id.switch_remaining_reps)
        val switchNotifications = view.findViewById<com.google.android.material.switchmaterial.SwitchMaterial>(R.id.switch_notifications)

        switchArHeatmap.setOnCheckedChangeListener { _, isChecked ->
            val message = if (isChecked) "AR‑Heatmap aktiviert" else "AR‑Heatmap deaktiviert"
            android.widget.Toast.makeText(requireContext(), message, android.widget.Toast.LENGTH_SHORT).show()
        }
        switchRemainingReps.setOnCheckedChangeListener { _, isChecked ->
            val message = if (isChecked) "Verbleibende Wiederholungen einblenden" else "Verbleibende Wiederholungen ausblenden"
            android.widget.Toast.makeText(requireContext(), message, android.widget.Toast.LENGTH_SHORT).show()
        }
        switchNotifications.setOnCheckedChangeListener { _, isChecked ->
            val message = if (isChecked) "Benachrichtigungen aktiviert" else "Benachrichtigungen deaktiviert"
            android.widget.Toast.makeText(requireContext(), message, android.widget.Toast.LENGTH_SHORT).show()
        }

        // Help icon displays a toast message.  In a full app this would navigate to a help page.
        val helpIcon = view.findViewById<android.widget.ImageView>(R.id.icon_help)
        helpIcon.setOnClickListener {
            android.widget.Toast.makeText(requireContext(), "Hilfe wird bald verfügbar sein", android.widget.Toast.LENGTH_SHORT).show()
        }
    }
}