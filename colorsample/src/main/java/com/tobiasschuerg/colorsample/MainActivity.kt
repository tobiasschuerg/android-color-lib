package com.tobiasschuerg.colorsample

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.Toolbar
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.updatePadding
import com.tobiasschuerg.color.ColorCreator
import com.tobiasschuerg.color.material.MaterialColor
import com.tobiasschuerg.color.models.getTextBlackWhite

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val rootView = findViewById<android.view.ViewGroup>(R.id.activity_main)
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        val contentLayout = findViewById<android.view.ViewGroup>(R.id.content_layout)

        // Handle window insets for edge-to-edge
        ViewCompat.setOnApplyWindowInsetsListener(rootView) { _, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())

            // Apply top inset to toolbar
            toolbar.updatePadding(top = systemBars.top)

            // Apply bottom inset to content
            contentLayout.updatePadding(bottom = systemBars.bottom)

            insets
        }

        setSupportActionBar(toolbar)

        // Generate a random material color
        val materialColor = MaterialColor(ColorCreator.randomColor())

        // Set toolbar colors
        val primaryColor = materialColor.get500()
        toolbar.setBackgroundColor(primaryColor.toColor())
        toolbar.setTitleTextColor(materialColor.getTextBlackWhite())

        // Set status bar color to transparent for edge-to-edge
        window.statusBarColor = android.graphics.Color.TRANSPARENT
        window.navigationBarColor = android.graphics.Color.TRANSPARENT

        // Set up material color variants on black background
        setupColorVariant(R.id.color_100_black, materialColor.get100())
        setupColorVariant(R.id.color_200_black, materialColor.get200())
        setupColorVariant(R.id.color_300_black, materialColor.get300())
        setupColorVariant(R.id.color_400_black, materialColor.get400())
        setupColorVariant(R.id.color_500_black, materialColor.get500())
        setupColorVariant(R.id.color_600_black, materialColor.get600())
        setupColorVariant(R.id.color_700_black, materialColor.get700())
        setupColorVariant(R.id.color_800_black, materialColor.get800())
        setupColorVariant(R.id.color_900_black, materialColor.get900())

        // Set up material color variants on white background
        setupColorVariant(R.id.color_100_white, materialColor.get100())
        setupColorVariant(R.id.color_200_white, materialColor.get200())
        setupColorVariant(R.id.color_300_white, materialColor.get300())
        setupColorVariant(R.id.color_400_white, materialColor.get400())
        setupColorVariant(R.id.color_500_white, materialColor.get500())
        setupColorVariant(R.id.color_600_white, materialColor.get600())
        setupColorVariant(R.id.color_700_white, materialColor.get700())
        setupColorVariant(R.id.color_800_white, materialColor.get800())
        setupColorVariant(R.id.color_900_white, materialColor.get900())

        val restartButton = findViewById<AppCompatButton>(R.id.restart_button)
        restartButton.setOnClickListener {
            startActivity(Intent(applicationContext, MainActivity::class.java))
            finish()
        }
    }

    private fun setupColorVariant(viewId: Int, color: com.tobiasschuerg.color.models.HSLColor) {
        val textView = findViewById<TextView>(viewId)
        textView.setBackgroundColor(color.toColor())
        // Set appropriate text color based on the color's lightness
        textView.setTextColor(color.getTextBlackWhite())
    }
}
