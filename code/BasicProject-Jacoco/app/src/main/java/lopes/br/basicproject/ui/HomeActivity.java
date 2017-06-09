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
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import lopes.br.basicproject.R;
import lopes.br.basicproject.model.Place;
import lopes.br.basicproject.ui.fragment.HomeFragment;

public class HomeActivity extends BaseActivity implements HomeFragment.ClickCallback {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Configuring the toolbar
        toolbarTxt.setText(getString(R.string.toolbar_home));
    }

    @Override
    public Fragment loadFragment() {
        return HomeFragment.newInstance();
    }

    @Override
    public void itemSelected(@NonNull Place placeSelected) {
        if (placeSelected != null) {
            callDetailsActivity(placeSelected);
        }
    }

    private void callDetailsActivity(@NonNull Place placeSelected) {
        Intent callDetailActivity = new Intent(this, DetailsActivity.class);
        callDetailActivity.putExtra(DetailsActivity.EXTRA_PLACE, placeSelected);
        startActivity(callDetailActivity);
    }
}
