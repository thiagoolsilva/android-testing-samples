
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

package lopes.br.basicproject.ui.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

import java.util.List;

import lopes.br.basicproject.R;
import lopes.br.basicproject.model.Place;
import lopes.br.basicproject.ui.fragment.HomeFragment;

public class PlaceAdapter extends RecyclerView.Adapter<PlaceAdapter.MyViewHolder> {

    private Context context;
    private List<Place> places;
    private HomeFragment.ClickCallback callback;

    public PlaceAdapter(@NonNull Context mContext, @NonNull List<Place> places, HomeFragment.ClickCallback callback) {
        this.context = mContext;
        this.places = places;
        this.callback = callback;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.view_card_view, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        if (places != null && context != null) {
            Place place = places.get(position);

            // Load the title
            holder.title.setText(place.getTitle());

            // loading the content of image into the Thumbnail
            Picasso.with(context)
                    .load(place.getImageUrl())
                    // Begin -->
                    // This code will remove the cache of picasso because we use a single url to load all resources.
                    // Please don't use this strategy in your real project because it is removing the power of caching (LRU)
                    // of all request on ImageView
                    .memoryPolicy(MemoryPolicy.NO_CACHE, MemoryPolicy.NO_STORE)
                    .networkPolicy(NetworkPolicy.NO_CACHE)
                    // End <--
                    .into(holder.thumbnail);
        }
    }

    @Override
    public int getItemCount() {
        return places != null ? places.size() : 0;
    }


    // Custom ViewHolder
    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView title;
        public ImageView thumbnail;

        public MyViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.title);
            thumbnail = (ImageView) view.findViewById(R.id.thumbnail);
            thumbnail.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            final int positionClicked = getAdapterPosition();
            final Place placeSelected = places != null && places.size() >= positionClicked ?
                    places.get(positionClicked) : null;

            // Check if the callback is not null before use it
            if (callback != null) {
                callback.itemSelected(placeSelected);
            }
        }
    }


}
