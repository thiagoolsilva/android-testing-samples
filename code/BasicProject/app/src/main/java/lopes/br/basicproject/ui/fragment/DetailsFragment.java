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

package lopes.br.basicproject.ui.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import lopes.br.basicproject.R;
import lopes.br.basicproject.model.Place;

/**
 * The type Details fragment.
 */
public class DetailsFragment extends Fragment {

    private Place place;
    private TextView textView;

    /**
     * Instantiates a new Details fragment.
     */
    public DetailsFragment() {
    }

    /**
     * Instantiates a new Details fragment.
     *
     * @param place the place
     */
    public DetailsFragment(@NonNull Place place) {
        this.place = place;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_details, null);

        configureComponents(view);
        loadContent();

        return view;
    }

    private void configureComponents(@NonNull View view) {
        textView = (TextView) view.findViewById(R.id.description);
    }

    private void loadContent() {
        if (this.place != null) {
            this.textView.setText(this.place.getDescription());
        }
    }

    /**
     * New instance details fragment.
     *
     * @param place the place
     * @return the details fragment
     */
    public static DetailsFragment newInstance(@NonNull Place place) {
        return new DetailsFragment(place);
    }


}
