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
 */

package lopes.br.basicproject.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.MenuItem;

import lopes.br.basicproject.model.Place;
import lopes.br.basicproject.ui.fragment.DetailsFragment;

/**
 * The type Details activity.
 */
public class DetailsActivity extends BaseActivity {

    /**
     * The constant EXTRA_PLACE.
     */
    public static final String EXTRA_PLACE = "lopes.br.basicproject.EXTRA_PLACE";
    private Place place;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        configureToolbar();
    }

    private void configureToolbar() {
        if (this.place != null) {
            toolbarTxt.setText(place.getTitle());
        }
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public Fragment loadFragment() {
        // try to get the given place
        final Intent intentReceived = getIntent();
        if (intentReceived != null) {
            this.place = (Place) intentReceived.getSerializableExtra(EXTRA_PLACE);
        }
        // Load the fragment with the place received
        return DetailsFragment.newInstance(place);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                super.onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
