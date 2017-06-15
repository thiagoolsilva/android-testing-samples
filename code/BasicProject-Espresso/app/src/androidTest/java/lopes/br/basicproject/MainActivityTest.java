/*
 *
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 *
 */
package lopes.br.basicproject;

import android.support.test.espresso.Espresso;
import android.support.test.espresso.action.ViewActions;
import android.support.test.espresso.assertion.ViewAssertions;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import lopes.br.basicproject.ui.MainActivity;
import lopes.br.basicproject.util.Constants;
import lopes.br.basicproject.util.SharedPref;

import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

    private static SharedPref sharedPref;

    // Configure the Activity under test
    @Rule
    public ActivityTestRule<MainActivity> activityTestRule =
            new ActivityTestRule<>(MainActivity.class, false, false);

    @BeforeClass
    public static void setUpStatic() {
        sharedPref = SharedPref.getInstance();
    }

    @Before
    public void setUp() {
        // Clear all sharedPreferences
        sharedPref.clearSharedPref();
    }

    @Test
    public void success_login_test() {
        //Start Manually the Activity
        activityTestRule.launchActivity(null);

        // Type the login
        Espresso.onView(withId(R.id.username))
                .check(ViewAssertions.matches(isDisplayed()))
                .perform(ViewActions.typeText(Constants.USER), ViewActions.closeSoftKeyboard());

        // Type the password
        Espresso.onView(withId(R.id.password))
                .check(ViewAssertions.matches(isDisplayed()))
                .perform(ViewActions.typeText(Constants.PASSWORD), ViewActions.closeSoftKeyboard());

        // Click on login button
        Espresso.onView(withId(R.id.btn_login))
                .check(ViewAssertions.matches(isDisplayed()))
                .perform(ViewActions.click());

        // Check if the another activity is being displayed
        Espresso.onView(allOf(withId(R.id.toolbar_title), withText(R.string.toolbar_home)))
                .check(ViewAssertions.matches(isDisplayed()));
    }

    @Test
    public void failed_login_test() {
        //Start Manually the Activity
        activityTestRule.launchActivity(null);

        // Type the login
        Espresso.onView(withId(R.id.username))
                .check(ViewAssertions.matches(isDisplayed()))
                .perform(ViewActions.typeText(Constants.USER), ViewActions.closeSoftKeyboard());

        // Type the password
        Espresso.onView(withId(R.id.password))
                .check(ViewAssertions.matches(isDisplayed()))
                .perform(ViewActions.typeText(Constants.WRONG_PASSWORD), ViewActions.closeSoftKeyboard());

        // Click on login button
        Espresso.onView(withId(R.id.btn_login))
                .check(ViewAssertions.matches(isDisplayed()))
                .perform(ViewActions.click());

        // Check if the another activity is being displayed
        Espresso.onView(allOf(withId(R.id.toolbar_title), withText(R.string.toolbar_home)))
                .check(ViewAssertions.doesNotExist());
    }

    @Test
    public void failed_warning_message_test() {
        //Start Manually the Activity
        activityTestRule.launchActivity(null);

        // Type the login
        Espresso.onView(withId(R.id.username))
                .check(ViewAssertions.matches(isDisplayed()))
                .perform(ViewActions.typeText(Constants.USER), ViewActions.closeSoftKeyboard());

        // Type the password
        Espresso.onView(withId(R.id.password))
                .check(ViewAssertions.matches(isDisplayed()))
                .perform(ViewActions.typeText(Constants.WRONG_PASSWORD), ViewActions.closeSoftKeyboard());

        // Click on login button
        Espresso.onView(withId(R.id.btn_login))
                .check(ViewAssertions.matches(isDisplayed()))
                .perform(ViewActions.click());

        Espresso.onView(withText(R.string.failed_password))
                .check(ViewAssertions.matches(isDisplayed()));
    }
}
