package com.example.colabore.login

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.colabore.R
import com.example.colabore.ScreenRobot
import com.example.colabore.ScreenRobot.Companion.withRobot
import com.example.colabore.ui.login.LoginActivity
import com.example.colabore.ui.login.LoginContract
import org.junit.Test
import org.junit.runner.RunWith
import java.util.regex.Pattern.matches

@RunWith(AndroidJUnit4::class)
class LoginActivityTest {
    @Test
    fun appLaunchesSuccessfully() {
        ActivityScenario.launch(LoginActivity::class.java)
    }

    @Test
    fun onLaunchCheckDocumentInputIsDisplayed() {
        ActivityScenario.launch(LoginActivity::class.java)
        Espresso.onView(withId(R.id.loginCpfEt))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

    @Test
    fun onLaunchCheckPasswordInputIsDisplayed() {
        ActivityScenario.launch(LoginActivity::class.java)
        Espresso.onView(withId(R.id.loginPasswordEt))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

    @Test
    fun onLaunchEnterButtonIsDisplayed() {
        ActivityScenario.launch(LoginActivity::class.java)

        Espresso.onView(withText(R.string.btn_enter))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

    @Test
    fun onLaunchSignUpButtonIsDisplayed() {
        ActivityScenario.launch(LoginActivity::class.java)

        Espresso.onView(withText(R.string.btn_sign_up))
            .check(ViewAssertions.matches(isDisplayed()))
    }

    @Test
    fun inputDocumentPasswordAndClickInSignIn() {
        ActivityScenario.launch(LoginActivity::class.java)
        withRobot(LoginScreenRobot::class.java)
            .inputDocument("48225692845")
                .inputPassword("luiz290198")
                  .clickOkOnView(R.id.loginSigninBtn)

    }

    class LoginScreenRobot : ScreenRobot<LoginScreenRobot>() {
        fun inputDocument(document: String):
          LoginScreenRobot {
            return enterTextIntoView(R.id.loginCpfEt, document)
        }

        fun inputPassword(password: String):
          LoginScreenRobot {
            return enterTextIntoView(R.id.loginPasswordEt, password)
        }

        fun inputDocumentAndPasswordClickinEnter(document: String ,password: String):
          LoginScreenRobot {
            return inputDocument(document)
                .inputPassword(password).clickOkOnView(R.id.loginSigninBtn)

        }
    }

}