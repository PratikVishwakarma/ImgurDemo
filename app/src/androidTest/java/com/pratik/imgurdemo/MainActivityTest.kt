package com.pratik.imgurdemo


import android.os.SystemClock
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.doesNotExist
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4ClassRunner::class)
class MainActivityTest {
    @get:Rule
    val activityScenario = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun launchApplication() {
        onView(withId(R.id.iv_app_logo)).check(matches(isDisplayed()))
    }

    @Test
    fun searchForImage() {
        onView(withId(R.id.iv_app_logo)).check(matches(isDisplayed()))
        SystemClock.sleep(3000)

        onView(withText(R.string.search_image)).check(matches(isDisplayed()))

        onView(withId(R.id.my_dialog_edit_text)).perform(typeText("cat"))

        onView(withText(R.string.search)).perform(click())

        onView(withText(R.string.search_image)).check(doesNotExist())
    }

}