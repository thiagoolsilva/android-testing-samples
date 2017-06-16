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

import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.support.test.uiautomator.By;
import android.support.test.uiautomator.UiDevice;
import android.support.test.uiautomator.UiObject2;
import android.support.test.uiautomator.Until;
import android.widget.Button;
import android.widget.EditText;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import lopes.br.basicproject.ui.MainActivity;
import lopes.br.basicproject.util.Constants;
import lopes.br.basicproject.util.SharedPref;

@RunWith(AndroidJUnit4.class)
public class MainActivityUiAutomatorTest {

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
        final String packageName = "lopes.br.basicproject";
        final String idUserResource = "lopes.br.basicproject:id/username";
        final String idPasswordResource = "lopes.br.basicproject:id/password";
        final String idButtonResource = "lopes.br.basicproject:id/btn_login";

        //Start Manually the Activity
        activityTestRule.launchActivity(null);

        // Get a reference of UIAutomator2
        UiDevice uiDevice = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation());

        // Type the text in User field
        final UiObject2 userObjectFound = uiDevice.wait(Until.findObject(By.clazz(EditText.class).pkg(packageName).res(idUserResource)), Constants.TIME_OUT_2_SECONDS);
        // Check if the user properly was found
        Assert.assertNotNull("Object user not found", userObjectFound);
        // Action
        userObjectFound.setText(Constants.USER);

        // Type the text in password field
        final UiObject2 passwordObjectFound = uiDevice.wait(Until.findObject(By.clazz(EditText.class).pkg(packageName).res(idPasswordResource)), Constants.TIME_OUT_2_SECONDS);
        // Check if the user properly was found
        Assert.assertNotNull("Object password not found", passwordObjectFound);
        // Action
        passwordObjectFound.setText(Constants.PASSWORD);

        // Find the Button object
        final UiObject2 buttonObjectFound = uiDevice.wait(Until.findObject(By.clazz(Button.class).pkg(packageName).res(idButtonResource)), Constants.TIME_OUT_2_SECONDS);
        // Check if the user properly was found
        Assert.assertNotNull("Object password not found", buttonObjectFound);
        // Action
        final Boolean result = buttonObjectFound.clickAndWait(Until.newWindow(), Constants.TIME_OUT_2_SECONDS);
        // Check for the result
        Assert.assertTrue("The new windows was not opened", result);
    }
}
