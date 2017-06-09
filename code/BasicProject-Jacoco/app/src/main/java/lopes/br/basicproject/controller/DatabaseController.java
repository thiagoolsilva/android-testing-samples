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

package lopes.br.basicproject.controller;

import com.thedeanda.lorem.Lorem;
import com.thedeanda.lorem.LoremIpsum;

import java.util.ArrayList;
import java.util.List;

import lopes.br.basicproject.model.Place;
import lopes.br.basicproject.util.Constants;

import static lopes.br.basicproject.util.Constants.MAX_PLACES;
import static lopes.br.basicproject.util.Constants.PREFIX_TITLE;

/**
 * The type Database controller.
 */
public class DatabaseController {


    private DatabaseController() {
    }

    /**
     * Create places list.
     *
     * @return the list
     */
    public static List<Place> createPlaces() {
        Lorem lorem = LoremIpsum.getInstance();
        List<Place> places = new ArrayList<>();
        Place place;

        for (int count = 0; count < MAX_PLACES; count++) {
            place = new Place();

            // build the place
            place.setTitle(PREFIX_TITLE + count);

            // build the description
            place.setDescription(lorem.getParagraphs(2, 4));

            // build the imageUrl
            place.setImageUrl(Constants.IMAGE_URL);

            // add the place into the List
            places.add(place);

        }
        return places;
    }
}
