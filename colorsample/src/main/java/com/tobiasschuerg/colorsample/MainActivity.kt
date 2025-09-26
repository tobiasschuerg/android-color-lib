package com.tobiasschuerg.colorsample

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.annotation.ColorInt
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.Toolbar
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.updatePadding
import com.google.android.material.textfield.TextInputLayout
import com.tobiasschuerg.color.ColorCreator
import com.tobiasschuerg.color.helper.setAccentColor
import com.tobiasschuerg.color.material.MaterialColor
import com.tobiasschuerg.color.models.ColorModel
import com.tobiasschuerg.color.models.HEXColor
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
        ViewCompat.setOnApplyWindowInsetsListener(rootView) { view, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())

            // Apply top inset to toolbar
            toolbar.updatePadding(top = systemBars.top)

            // Apply bottom inset to content
            contentLayout.updatePadding(bottom = systemBars.bottom)

            insets
        }

        setSupportActionBar(toolbar)

        val materialColor = MaterialColor(ColorCreator.randomColor())
        val color1 = materialColor.get500()
        toolbar.setBackgroundColor(color1.toColor())
        toolbar.setTitleTextColor(materialColor.getTextBlackWhite())

        // Set status bar color to transparent for edge-to-edge
        window.statusBarColor = android.graphics.Color.TRANSPARENT
        window.navigationBarColor = android.graphics.Color.TRANSPARENT

        val color: ColorModel = HEXColor("#ffaabbff")
                .toHSV()
                .copy(saturation = 0.6f)
                .copy(hue = 190f)
                .toHSL()
                .copy(lightness = 0.5f)

        val hello = findViewById<TextView>(R.id.hello)
        hello.setTextColor(color.toColor())

        val firstNameLayout = findViewById<TextInputLayout>(R.id.first_name_layout)
        val lastNameLayout = findViewById<TextInputLayout>(R.id.last_name_layout)
        firstNameLayout.setAccentColor(color1.toColor())
        lastNameLayout.setAccentColor(color1.toColor())

        val restartButton = findViewById<AppCompatButton>(R.id.restart_button)
        restartButton.setOnClickListener {
            startActivity(Intent(applicationContext, MainActivity::class.java))
            finish()
        }
    }
}
