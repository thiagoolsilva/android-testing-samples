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

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.widget.TextView;

import com.robotium.solo.Solo;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;

import lopes.br.basicproject.ui.HomeActivity;
import lopes.br.basicproject.util.Constants;
import lopes.br.basicproject.util.SharedPref;

import static android.support.test.InstrumentationRegistry.getInstrumentation;

@RunWith(AndroidJUnit4.class)
public class HomeActivityTest {
    private Solo solo;
    private static SharedPref sharedPref;


    // Configure the Activity under test
    @Rule
    public ActivityTestRule<HomeActivity> activityTestRule =
            new ActivityTestRule<>(HomeActivity.class);

    @BeforeClass
    public static void setUpStatic() {
        sharedPref = SharedPref.getInstance();
        // Clear all sharedPreferences
        sharedPref.setUserLoggin(true);
    }

    @Before
    public void setUp() {
        // Get a reference of Robotium´s class
        solo = new Solo(getInstrumentation(),
                activityTestRule.getActivity());
    }


    @Test
    public void click_in_list_test() {
        final int lineIndex = 0;
        final char suffix = '0';
        boolean result = false;
        final ArrayList<TextView> textViews = solo.clickInRecyclerView(lineIndex);

        Assert.assertNotNull("Line not found", textViews);

        // check if the first item has the string Topic 0
        if (textViews != null) {
            for (TextView buffer : textViews) {
                if (buffer.getText().equals(Constants.PREFIX_TITLE + suffix)) {
                    result = true;
                }
            }
            // Assert the textview found
            Assert.assertTrue("Text not found", result);
        }
    }

    @After
    public void tearDown() {
        //finishOpenedActivities() will finish all the activities that have been opened during the test execution.
        solo.finishOpenedActivities();
    }

}
