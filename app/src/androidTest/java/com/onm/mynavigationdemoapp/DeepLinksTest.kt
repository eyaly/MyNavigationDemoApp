package com.onm.mynavigationdemoapp

import android.content.Intent
import android.net.Uri
import android.widget.TextView
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.intent.Intents
import androidx.test.espresso.intent.Intents.intended
import androidx.test.espresso.intent.rule.IntentsTestRule
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import org.junit.Assert
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
class DeepLinksTest{

    val intentLink = Intent(Intent.ACTION_VIEW, Uri.parse("mynavapp://third-activity/SauceLabs"))

    @get: Rule
    val activityRule: ActivityScenarioRule<MainActivity> = ActivityScenarioRule<MainActivity>(intentLink)


//    @Test
//    fun test_navWithDeepLinkActivity(){
//        val activityScenario = ActivityScenario.launch(MainActivity::class.java)
//        Intents.init()
//
//        val intentLink = Intent(Intent.ACTION_VIEW, Uri.parse("mynavapp://third-activity/SauceLabs"))
//        activityScenario.launchActivity(intentLink)
////        intended(
////            allOf(
////                hasComponent(NewActivity::class.java.name)
////            )
////        )
//        Intents.release()
//
//    }

    @Test
    fun test_deepLinkApplicationThirdPageText(){

        onView(withId(R.id.ThirdScreenTextView))
            .check(matches(withText("SauceLabsxx")))
    }

}