package com.onm.mynavigationdemoapp

import androidx.test.espresso.Espresso
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.onm.mynavigationdemoapp.annotations.BasicFlow
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
class SecondaryActivityTest{

    @get: Rule
    val activityRule:ActivityScenarioRule<SecondActivity> = ActivityScenarioRule(SecondActivity::class.java)

    @Test
    @BasicFlow
    fun test_isActivityInView(){
        // The basic one - did the app launched and in view
        Espresso.onView(ViewMatchers.withId(R.id.btnToThirdActivity))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

    @Test
    @BasicFlow
    fun test_isActivityInViewError(){
        // The basic one - did the app launched and in view
        Espresso.onView(ViewMatchers.withId(R.id.btnToThirdActivity))
            .check(ViewAssertions.matches(ViewMatchers.isNotClickable()))
    }


}