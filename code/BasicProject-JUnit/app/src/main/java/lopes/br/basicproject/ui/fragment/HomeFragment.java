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

package lopes.br.basicproject.ui.fragment;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import lopes.br.basicproject.R;
import lopes.br.basicproject.controller.DatabaseController;
import lopes.br.basicproject.model.Place;
import lopes.br.basicproject.ui.adapter.PlaceAdapter;

/**
 * The type Details fragment.
 */
public class HomeFragment extends Fragment {

    private RecyclerView recyclerView;
    private ClickCallback callback;

    /**
     * New instance details fragment.
     *
     * @return the details fragment
     */
    public static HomeFragment newInstance() {
        HomeFragment homeFragment = new HomeFragment();
        return homeFragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof ClickCallback) {
            callback = (ClickCallback) context;
        } else {
            throw new IllegalArgumentException("The activity must implement the interface ClickCallback");
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (activity instanceof ClickCallback) {
            callback = (ClickCallback) activity;
        } else {
            throw new IllegalArgumentException("The activity must implement the interface ClickCallback");
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, null);

        buildListContent(view);

        return view;
    }

    private void buildListContent(@NonNull View view) {
        this.recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);

        PlaceAdapter adapter = new PlaceAdapter(getActivity(), DatabaseController.createPlaces(), callback);
        recyclerView.setAdapter(adapter);

        //Set the LayoutManager
        this.recyclerView.setHasFixedSize(true);
        this.recyclerView.setNestedScrollingEnabled(false);

        LinearLayoutManager llm = new LinearLayoutManager(getContext());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        this.recyclerView.setLayoutManager(llm);
    }

    public interface ClickCallback {

        public void itemSelected(@NonNull Place placeSelected);
    }
}

