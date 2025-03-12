package com.onm.mynavigationdemoapp

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Build
import android.os.Environment
import androidx.annotation.RequiresApi
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.espresso.screenshot.captureToBitmap
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import androidx.test.platform.app.InstrumentationRegistry
//import au.com.boq.feature.customerProfile.features.helpAndSupport.home.feature.commitFileToRepository
import com.onm.mynavigationdemoapp.annotations.BasicFlow
import com.onm.mynavigationdemoapp.annotations.NavFlow
import org.junit.Assert.*
import org.junit.Ignore
import org.junit.Test
import org.junit.runner.RunWith
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import java.lang.System.out

@RunWith(AndroidJUnit4ClassRunner::class)
class MainActivityTest {

    @Test
    @BasicFlow
    fun test_isActivityInView() {
        // The basic one - did the app launched and in view
        val activityScenario = ActivityScenario.launch(MainActivity::class.java)
        onView(withId(R.id.mainScreenText)).check(matches(isDisplayed()))
    }

    @Test
    @BasicFlow
    fun test_visibility_nextButton() {
        // The basic one - did the app launched and in view
        val activityScenario = ActivityScenario.launch(MainActivity::class.java)
        onView(withId(R.id.btnToSecondActivity)).check(matches(isDisplayed()))
    }

    @Test
    @BasicFlow
    fun test_isTitleTextDisplayedError() {
        // The basic one - did the app launched and in view
        val activityScenario = ActivityScenario.launch(MainActivity::class.java)
        onView(withId(R.id.mainScreenText))
            .check(matches(withText(R.string.text_main_activity_error)))
    }

    @Test
    @BasicFlow
    @Ignore
    fun test_isTitleTextDisplayed() {
        // The basic one - did the app launched and in view
        val activityScenario = ActivityScenario.launch(MainActivity::class.java)
        onView(withId(R.id.mainScreenText))
            .check(matches(withText(R.string.text_main_activity)))
    }

    @Test
    @NavFlow
    @Ignore
    fun test_navSecondaryActivity() {
        val activityScenario = ActivityScenario.launch(MainActivity::class.java)
        onView(withId(R.id.btnToSecondActivity)).perform(click())
        // Verify we in the second page
        onView(withId(R.id.btnToThirdActivity))
            .check(matches(isDisplayed()))
    }

    @Test
    @NavFlow
    @Ignore
    fun test_checkTextToSecondaryActivity() {
        val textToSend = "Testing Espresso Test"
        // Check if the text pass from the main to the second activity
        val activityScenario = ActivityScenario.launch(MainActivity::class.java)

        // set text
        onView(withId(R.id.textToSendSecondScreen)).perform(
            clearText(),
            typeText(textToSend),
            closeSoftKeyboard()
        )
//        Espresso.closeSoftKeyboard()
        // click the btn
        onView(withId(R.id.btnToSecondActivity)).perform(click())

        // Verify we in the second page
        onView(withId(R.id.btnToThirdActivity))
            .check(matches(isDisplayed()))

        // And verify the text is as expected
        onView(withId(R.id.secondScreenText))
            .check(matches(withText(textToSend)))

        SauceLabsCustomScreenshot.capture("eyal-screen-2-navigation")
        //val bitmap = onView(isRoot()).captureToBitmap()
        //bitmap.writeToTestStorage (nameRule.methodName)
        //assertScreenshotMatchesGolden (goldenName = "test_checkTextToSecondaryActivity", bitmap)
    }


    private fun assertScreenshotMatchesGolden(
        goldenName: String,
        bitmap: Bitmap
    ) {
        // Save screenshot to file for debugging
        val filename = goldenName + System.currentTimeMillis().toString()
        val filePath = saveScreenshot(filename, bitmap)
        //SauceLabsCustomScreenshot.capture ("Sauce EspressoCapture-HelpAndSupport")
        val golden = InstrumentationRegistry.getInstrumentation()
        .context.resources.assets.open("$goldenName.png").use { BitmapFactory.decodeStream(it) }

    }

    private fun saveScreenshot (filename: String, bmp: Bitmap): String {
        val downloadsDir: File =
        Environment.getExternalStoragePublicDirectory (Environment.DIRECTORY_DOWNLOADS)
        val screenshotsDir = File (downloadsDir, "sauce_labs_custom_screenshots")
        if (!screenshotsDir.exists()) {
            screenshotsDir.mkdir()
        }
        val path = screenshotsDir.absolutePath
        FileOutputStream("$path/$filename.png").use { out ->
            bmp.compress(Bitmap.CompressFormat.PNG, 100, out)
        }
        return "$path/$filename.png"
    }

}