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

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import lopes.br.basicproject.R;
import lopes.br.basicproject.ui.fragment.HomeFragment;
import lopes.br.basicproject.util.Constants;

/**
 * The type Base activity.
 */
public abstract class BaseActivity extends AppCompatActivity {

    protected TextView toolbarTxt;
    protected Toolbar toolbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        initComponents();
        replaceFragment(loadFragment(), R.id.content);
    }

    private void initComponents() {
        this.toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        toolbarTxt = (TextView) toolbar.findViewById(R.id.toolbar_title);
        // Configuring the ActionBar
        setTitle(Constants.EMPTY_STRING);
    }

    /**
     * Replace fragment.
     *
     * @param fragment    the fragment
     * @param placeHolder the place holder
     */
    protected void replaceFragment(@NonNull Fragment fragment, @IdRes int placeHolder) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        if (fragmentManager.findFragmentByTag(HomeFragment.class.toString()) == null) {
            fragmentTransaction.replace(R.id.content, fragment, fragment.getClass().toString());
        }
        fragmentTransaction.commitAllowingStateLoss();
    }

    public abstract Fragment loadFragment();
}
