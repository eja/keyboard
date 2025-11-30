// Copyright (C) by Ubaldo Porcheddu <ubaldo@eja.it>

package it.eja.keyboard

import android.app.Activity
import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.provider.Settings
import android.view.Gravity
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView

class MainActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val layout = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            gravity = Gravity.CENTER_HORIZONTAL
            setBackgroundColor(Color.parseColor("#121212"))
            setPadding(40, 100, 40, 40)
        }

        val title = TextView(this).apply {
            text = "EJA KEYBOARD"
            textSize = 32f
            typeface = Typeface.MONOSPACE
            setTextColor(Color.CYAN)
            gravity = Gravity.CENTER
            setPadding(0, 0, 0, 60)
        }

        val testInput = EditText(this).apply {
            hint = "Tap here to test keyboard..."
            setHintTextColor(Color.GRAY)
            setTextColor(Color.WHITE)
            setBackgroundColor(Color.parseColor("#252525"))
            setPadding(30, 30, 30, 30)
            textSize = 18f
            layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            ).apply { setMargins(0, 0, 0, 80) }
        }

        val btnSetup = Button(this).apply {
            text = "1. ENABLE IN SETTINGS"
            setBackgroundColor(Color.parseColor("#006064"))
            setTextColor(Color.WHITE)
            setOnClickListener {
                startActivity(Intent(Settings.ACTION_INPUT_METHOD_SETTINGS))
            }
        }

        val btnSelect = Button(this).apply {
            text = "2. SELECT KEYBOARD"
            setBackgroundColor(Color.parseColor("#004D40"))
            setTextColor(Color.WHITE)
            layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            ).apply { setMargins(0, 40, 0, 0) }
            setOnClickListener {
                getSystemService(InputMethodManager::class.java).showInputMethodPicker()
            }
        }

        layout.addView(title)
        layout.addView(testInput)
        layout.addView(btnSetup)
        layout.addView(btnSelect)

        setContentView(layout)
    }
}
