package lopes.br.basicproject;

import org.junit.Assert;
import org.junit.Test;

import java.util.List;

import lopes.br.basicproject.controller.DatabaseController;
import lopes.br.basicproject.model.Place;
import lopes.br.basicproject.util.Constants;

public class DatabaseControllerTest {

    // [Warning]: Our data will be always the same because we're not using flexible data. So the below example
    // will be used only for the presentation

    // [TIP]: You can test your data that came from database using the RenamingDelegatingContext
    // to avoid problem with the real data stored
    @Test
    public void createPlacesTest() {
        List<Place> places = DatabaseController.createPlaces();
        int count = 0;

        // Check if all required field are valid
        for (Place place : places) {
            // Checking the title
            Assert.assertNotSame("Check the title", place.getTitle(), Constants.PREFIX_TITLE + count);

            // Checking the description
            Assert.assertNotNull("The description is null", place.getDescription());

            // Checking the imageUrl
            Assert.assertNotNull("The imageUrl is null", place.getImageUrl());

            count++;
        }
    }
}
