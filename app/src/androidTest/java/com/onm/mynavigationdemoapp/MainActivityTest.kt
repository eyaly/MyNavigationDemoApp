package com.onm.mynavigationdemoapp

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
class MainActivityTest{

    @Test
    fun test_isActivityInView(){
        // The basic one - did the app launched and in view
        val activityScenario = ActivityScenario.launch(MainActivity::class.java)
        onView(withId(R.id.mainScreenText)).check(matches(isDisplayed()))
    }

    @Test
    fun test_visibility_nextButton(){
        // The basic one - did the app launched and in view
        val activityScenario = ActivityScenario.launch(MainActivity::class.java)
        onView(withId(R.id.btnToSecondActivity)).check(matches(isDisplayed()))
    }

    @Test
    fun test_isTitleTextDisplayed(){
        // The basic one - did the app launched and in view
        val activityScenario = ActivityScenario.launch(MainActivity::class.java)
        onView(withId(R.id.mainScreenText))
            .check(matches(withText(R.string.text_main_activity)))
    }

    @Test
    fun test_navSecondaryActivity(){
        val activityScenario = ActivityScenario.launch(MainActivity::class.java)
        onView(withId(R.id.btnToSecondActivity)).perform(click())
        // Verify we in the second page
        onView(withId(R.id.btnToThirdActivity))
            .check(matches(isDisplayed()))
    }

    @Test
    fun test_checkTextToSecondaryActivity(){
        val textToSend = "Testing Espresso Test"
        // Check if the text pass from the main to the secind activity
        val activityScenario = ActivityScenario.launch(MainActivity::class.java)

        // set text
        onView(withId(R.id.textToSendSecondScreen)).perform(clearText(), typeText(textToSend), closeSoftKeyboard())
//        Espresso.closeSoftKeyboard()
        // click the btn
        onView(withId(R.id.btnToSecondActivity)).perform(click())

        // Verify we in the second page
        onView(withId(R.id.btnToThirdActivity))
            .check(matches(isDisplayed()))

        // And verify the text is as expected
        onView(withId(R.id.secondScreenText))
            .check(matches(withText(textToSend)))
    }
}