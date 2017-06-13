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
package lopes.br.basicproject.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import lopes.br.basicproject.R;
import lopes.br.basicproject.controller.ValidatorController;
import lopes.br.basicproject.util.SharedPref;

public class MainActivity extends AppCompatActivity {


    //Layout
    private TextInputLayout userLayout;
    private TextInputLayout passwordLayout;

    //Button
    private Button loginBtn;

    //Listener
    private View.OnClickListener loginListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            validateAllFields();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initComponents();

        checkLoginStatus();
    }

    private void checkLoginStatus() {
        if (SharedPref.getInstance().isUserLogged()) {
            callDetailsAct();
            finishAffinity();
        }
    }

    // get a point of all views in the layout
    private void initComponents() {
        this.userLayout = (TextInputLayout) findViewById(R.id.wrapper_user);
        this.passwordLayout = (TextInputLayout) findViewById(R.id.wrapper_password);

        this.loginBtn = (Button) findViewById(R.id.btn_login);
        this.loginBtn.setOnClickListener(loginListener);
    }

    // validate if the USER + password are valid
    private void validateAllFields() {
        final String user = this.userLayout.getEditText().getText().toString();
        final String password = this.passwordLayout.getEditText().getText().toString();

        if (!ValidatorController.validateLogin(user, password)) {
            this.passwordLayout.setError(getString(R.string.failed_password));
        } else {
            // save the state of login
            SharedPref.getInstance().setUserLoggin(true);

            // call details act
            callDetailsAct();
        }
    }

    private void callDetailsAct() {
        // call the details act
        Intent openDefailsActivity = new Intent(this, HomeActivity.class);
        openDefailsActivity.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(openDefailsActivity);
    }
}
