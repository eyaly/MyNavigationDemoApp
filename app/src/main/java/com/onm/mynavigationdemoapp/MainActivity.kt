package com.onm.mynavigationdemoapp

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // From: https://www.mobot.io/blog/how-to-test-deep-links-in-android
        handleIntent(intent)

//        val action: String? = intent?.action
//        Log.i("deep", "action: " +  action)
    }

    fun onClickGoToSecondScreen(view : View){
        val textToSend = findViewById<View>(R.id.textToSendSecondScreen) as EditText
        val url: String = textToSend.getText().toString()
//        val intentSecondActivity = Intent(Intent.ACTION_VIEW, Uri.parse(url))

        val intentSecondActivity = Intent(this, SecondActivity::class.java)
        intentSecondActivity.putExtra(getString(R.string.KEY_EXTRA_MESSAGE), url);
//        intentSecondActivity.putExtra("sendText", url);
        startActivity(intentSecondActivity)

    }

    private fun handleIntent(intent: Intent?) {
        val appLinkAction: String? = intent?.action
        Log.i("deep", "action: " +  appLinkAction)
        if (appLinkAction.equals("android.intent.action.MAIN").not()) {
            val appLinkData: Uri? = intent?.data
            if (appLinkData != null) {
                processDeepLink(appLinkData)
            }
        }
    }

    private fun processDeepLink(appLinkData: Uri?) {

        Log.i("deep", "data: " +  appLinkData)
        // checking if the uri is null or not.
        if (appLinkData != null) {

            // if the uri is not null then we are getting
            // the path segments and storing it in list.
            val parameters: List<String> = appLinkData.getPathSegments()
            Log.i("deep", "param size: " +  parameters.size.toString())
            var param = "default value"
            // after that we are extracting string
            // from that parameters.
            if (parameters.size > 0){
                param = parameters[parameters.size - 1]
            }
            Log.i("deep", "param: " +  param)
            val intentSecondActivity = Intent(this, ThirdActivity::class.java)
            intentSecondActivity.putExtra(getString(R.string.KEY_EXTRA_MESSAGE), param);
            startActivity(intentSecondActivity)
        }
    }
}