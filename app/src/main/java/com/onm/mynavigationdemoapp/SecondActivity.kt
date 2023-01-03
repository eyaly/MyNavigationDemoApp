package com.onm.mynavigationdemoapp

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity


class SecondActivity : AppCompatActivity() {

    var valueText:String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
        val extras = this.intent.extras
        if (extras != null){
            valueText = extras!!.getString(getString(R.string.KEY_EXTRA_MESSAGE)).toString()
        } else {
            valueText = "default value!!!"
        }
        val secondTextView = findViewById<View>(R.id.secondScreenText) as TextView
        secondTextView.setText(valueText)
    }

    fun onClickGoToThirdScreen(view : View) {
     //   println(valueText)
        val intentThirdActivity = Intent(this, ThirdActivity::class.java)
        intentThirdActivity.putExtra(getString(R.string.KEY_EXTRA_MESSAGE), valueText);
        startActivity(intentThirdActivity)
    }
}