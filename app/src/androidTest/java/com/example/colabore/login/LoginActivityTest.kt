package com.example.colabore.login

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.colabore.R
import com.example.colabore.ui.login.LoginActivity
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class LoginActivityTest {
    @Test
    fun appLaunchesSuccessfully() {
        ActivityScenario.launch(LoginActivity::class.java)
    }

    @Test
    fun onLaunchCheckAmountInputIsDisplayed() {
        ActivityScenario.launch(LoginActivity::class.java)
        Espresso.onView(withId(R.id.loginCpfEt))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }
}