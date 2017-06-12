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
import android.test.RenamingDelegatingContext;

import com.robotium.solo.Solo;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import lopes.br.basicproject.ui.MainActivity;
import lopes.br.basicproject.util.SharedPref;

@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

    private static final String MOCK_CONTEXT_NAME = "_test";

    private Solo solo;
    private RenamingDelegatingContext mockContext;
    private SharedPref sharedPref;

    // Configure the Activity under test
    @Rule
    public ActivityTestRule<MainActivity> activityTestRule =
            new ActivityTestRule<>(MainActivity.class);

    @Before
    public void setUp() {
        // Configure the mock context without using mockito
        mockContext =  new RenamingDelegatingContext(InstrumentationRegistry.getTargetContext(), MOCK_CONTEXT_NAME);

        // Set the mocked context into the custom SharedPreferences
        sharedPref = SharedPref.getInstance();
        sharedPref.setContext(mockContext);

        // Get a reference of Robotium´s class
        solo = new Solo(InstrumentationRegistry.getInstrumentation(),
                activityTestRule.getActivity());
    }

    @Test
    public void success_login_test() {
        // TODO - finish later
    }

    @After
    public void tearDown() {
        //finishOpenedActivities() will finish all the activities that have been opened during the test execution.
        solo.finishOpenedActivities();
    }
}
