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
package lopes.br.basicproject.controller;

import android.support.annotation.NonNull;
import android.text.TextUtils;

import lopes.br.basicproject.util.Constants;

public class ValidatorController {

    // This class will have only static methods
    private ValidatorController() {
    }

    public static boolean validateLogin(@NonNull String user, @NonNull String password) {
        // first check!
        if (TextUtils.isEmpty(user) && TextUtils.isEmpty(password)) {
            return false;
        }

        // Check if the hard coded user and password match
        return user.equalsIgnoreCase(Constants.user) && password.equalsIgnoreCase(Constants.PASSWORD);
    }
}
