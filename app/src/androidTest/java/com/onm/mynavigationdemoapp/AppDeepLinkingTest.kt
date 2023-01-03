package com.onm.mynavigationdemoapp

import android.content.Intent
import android.net.Uri
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
class AppDeepLinkingTest{
    val textToSend = "Testing Espresso Test"
    // Base on: https://www.mobot.io/blog/how-to-test-deep-links-in-android
    // verifying that deep links result in the UI changes we expect. For this, we can use ActivityScenarioRule and Kotlin's ability to easily access views inside a unit test.

    val intentLink = Intent(Intent.ACTION_VIEW, Uri.parse("mynavapp://third-activity/"+textToSend))

    @get: Rule
    val activityRule: ActivityScenarioRule<MainActivity> = ActivityScenarioRule<MainActivity>(intentLink)


    @Test
    fun test_deepLinkApplicationThirdPageText(){

        onView(withId(R.id.ThirdScreenTextView))
            .check(matches(withText(textToSend)))
    }

}