package com.onm.mynavigationdemoapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView

class ThirdActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_third)

        val extras = this.intent.extras
        val valueText = extras!!.getString(getString(R.string.KEY_EXTRA_MESSAGE)).toString()
        val thirdTextView = findViewById<View>(R.id.ThirdScreenTextView) as TextView
        thirdTextView.setText(valueText)
    }
}