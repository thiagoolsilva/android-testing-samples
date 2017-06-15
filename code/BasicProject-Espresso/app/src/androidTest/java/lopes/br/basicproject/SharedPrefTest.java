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
import android.support.test.runner.AndroidJUnit4;
import android.test.RenamingDelegatingContext;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import lopes.br.basicproject.util.SharedPref;

@RunWith(AndroidJUnit4.class)
public class SharedPrefTest {

    // mocking the context without using mockito
    private RenamingDelegatingContext renamingDelegatingContext;

    @Before
    public void mockContext() {
        renamingDelegatingContext = new RenamingDelegatingContext(InstrumentationRegistry.getTargetContext(), "_test");
    }

    @Test
    public void isLoggedTest() {
        final SharedPref mockedPref = getMockedPref();

        // save the login
        mockedPref.setUserLoggin(true);

        // retrieve the result of login
        final boolean isLogged = mockedPref.isUserLogged();
        Assert.assertTrue("The USER is not logged", isLogged);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void isNotLoggedTest() {
        final SharedPref mockedPref = getMockedPref();

        // save the login
        mockedPref.setUserLoggin(false);

        // retrieve the result of login
        final boolean isLogged = mockedPref.isUserLogged();
        Assert.assertFalse("The USER is logged", isLogged);
    }

    @Test(expected = NullPointerException.class)
    public void trowExceptionTest() {
        final SharedPref mockedPref = null;

        mockedPref.isUserLogged();
    }

    @Test
    public void clearSharedPrefTest() {
        final SharedPref mockedPref = getMockedPref();

        // save a data into the sharedPreferences
        isLoggedTest();

        // clear the sharedPrerences
        mockedPref.clearSharedPref();

        // checking if the result is false
        final boolean isNotLogged = mockedPref.isUserLogged();
        Assert.assertFalse("The data was not removed properly", isNotLogged);

    }

    // Mock the context
    private SharedPref getMockedPref() {
        final SharedPref sharedPref = SharedPref.getInstance();
        sharedPref.setContext(renamingDelegatingContext);

        return sharedPref;
    }
}
