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
package lopes.br.basicproject.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import lopes.br.basicproject.R;
import lopes.br.basicproject.ui.fragment.DetailsFragment;
import lopes.br.basicproject.util.Constants;

public class DetailsActivity extends AppCompatActivity {

    private TextView toolbarTxt;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        initComponents();
        initFragment(savedInstanceState);
    }

    private void initFragment(Bundle savedInstanceState) {
            if (savedInstanceState == null) {
            DetailsFragment newFragment = DetailsFragment.newInstance();
            final FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.content, newFragment, DetailsFragment.class.toString());
        }
    }

    private void initComponents() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Configuring the toolbar
        toolbarTxt = (TextView) toolbar.findViewById(R.id.toolbar_title);
        toolbarTxt.setText(getString(R.string.toolbar_name));

        // Configuring the ActionBar
        setTitle(Constants.EMPTY_STRING);
    }
}
