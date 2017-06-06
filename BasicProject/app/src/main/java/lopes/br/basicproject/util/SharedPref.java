/*
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
package lopes.br.basicproject.util;

import android.content.Context;
import android.content.SharedPreferences;

import lopes.br.basicproject.App;
import lopes.br.basicproject.util.Constants.SharedPreference;

public class SharedPref {

    private static SharedPref sInstance;

    /**
     * Return a single instance of Shared Preferences stored into the app
     *
     * @return
     */
    public static SharedPref getInstance() {
        if (sInstance == null) {
            sInstance = new SharedPref();
        }
        return sInstance;
    }

    /**
     * Return a instance of SharedPreferences
     *
     * @return SharedPreferences
     */
    private SharedPreferences getPref() {
        return App.getContext().getSharedPreferences(SharedPreference.SHARED_PREF_NAME, Context.MODE_PRIVATE);
    }

    /**
     * Return if the user alredy logged into the system
     *
     * @return
     */
    public boolean isUserLogged() {
        return getPref().getBoolean(SharedPreference.KEY_USER_LOGGED, false);
    }


    /**
     * Set when the user enter in the system
     *
     * @param isLogged isLogged
     */
    public void setUserLoggin(boolean isLogged) {
        getPref().edit().putBoolean(SharedPreference.KEY_USER_LOGGED, isLogged).commit();
    }

    /**
     * Clear all sharedPreferences
     */
    public void clearSharedPref() {
        getPref().edit().clear().commit();
    }
}
