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


import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.robotium.solo.Solo;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import lopes.br.basicproject.ui.HomeActivity;
import lopes.br.basicproject.ui.MainActivity;
import lopes.br.basicproject.util.Constants;
import lopes.br.basicproject.util.SharedPref;

import static android.support.test.InstrumentationRegistry.getInstrumentation;

@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

    private static final String MOCK_CONTEXT_NAME = "_test";

    private Solo solo;
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

        // Get a reference of Robotium´s class
        solo = new Solo(getInstrumentation(),
                activityTestRule.getActivity());
    }

    @Test
    public void success_login_test() {
        fillAllRequiredFields(Constants.USER, Constants.PASSWORD, HomeActivity.class);
    }

    @Test
    public void fail_login_test() {
        fillAllRequiredFields(Constants.USER, Constants.WRONG_PASSWORD, MainActivity.class);
    }

    private void fillAllRequiredFields(@NonNull String user, @NonNull String password, @NonNull Class<? extends Activity> activity) {
        //Start Manually the Activity
        activityTestRule.launchActivity(null);

        //Unlock the lock screen
        solo.unlockScreen();

        //Send the USERNAME to the EditText
        solo.typeText(0, user);

        //Send the PASSWORD to the EditText
        solo.typeText(1, password);

        // Click on Login button
        String btnTxt = InstrumentationRegistry.getTargetContext().getString(R.string.enter);
        solo.clickOnButton(btnTxt);

        //Check if the MainActivity was opened
        solo.assertCurrentActivity("Wrong Activity", activity);
    }


    @After
    public void tearDown() {
        //finishOpenedActivities() will finish all the activities that have been opened during the test execution.
        solo.finishOpenedActivities();
    }
}
