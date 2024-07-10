package com.onm.mynavigationdemoapp

import android.graphics.Bitmap
import android.os.Environment
import android.util.Log
import androidx.test.runner.screenshot.BasicScreenCaptureProcessor
import androidx.test.runner.screenshot.Screenshot
import java.io.File
import java.util.concurrent.atomic.AtomicInteger


object SauceLabsCustomScreenshot {
    private val screenshotId = AtomicInteger(1)
    fun capture(screenshotName: String?) {
        var screenshotName = screenshotName
        try {
            screenshotName =
                String.format("%04d-%s", screenshotId.getAndIncrement(), screenshotName)
            val capture = Screenshot.capture()
            capture.format = Bitmap.CompressFormat.PNG
            capture.name = screenshotName
            val processor = SauceLabsScreenCaptureProcessor()
            processor.process(capture)
            val savedScreenshotPath = File(processor.getPathForFile(screenshotName) + ".png").path
            Log.i("SauceLabs", String.format("Screenshot saved to %s", savedScreenshotPath))
        } catch (e: Exception) {
            Log.e("SauceLabs", "Failed to capture screenshot", e)
        }
    }

    private class SauceLabsScreenCaptureProcessor : BasicScreenCaptureProcessor() {
        init {
            val downloadsDir =
                Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)
            val screenshotsDir = File(downloadsDir, SAUCE_LABS_CUSTOM_SCREENSHOTS)
            mDefaultScreenshotPath = screenshotsDir
        }

        fun getPathForFile(name: String?): String {
            val file = File(mDefaultScreenshotPath, getFilename(name))
            return file.absolutePath
        }

        companion object {
            private const val SAUCE_LABS_CUSTOM_SCREENSHOTS = "sauce_labs_custom_screenshots"
        }
    }


}


