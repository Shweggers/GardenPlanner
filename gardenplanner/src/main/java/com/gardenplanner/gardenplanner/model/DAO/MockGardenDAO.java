package com.gardenplanner.gardenplanner.model.DAO;

import com.gardenplanner.gardenplanner.model.Garden;
import com.gardenplanner.gardenplanner.model.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Mock class for the GardenDAO class
 */
public class MockGardenDAO implements IGardenDAO {
    private final ArrayList<Garden> gardens = new ArrayList<>();
    private int autoIncrementID = 1;

    /**
     * Insert a garden into the database
     *
     * @param garden the garden to insert
     */
    @Override
    public void insert(Garden garden) {
        Garden newGarden = garden.withID(autoIncrementID++);
        gardens.add(newGarden);
    }

    /**
     * Delete a garden from the database
     *
     * @param garden the garden to delete
     */
    @Override
    public void delete(Garden garden) {
        gardens.remove(garden);
    }

    /**
     * Get a list of gardens for a user
     *
     * @param userID the user ID
     * @return a list of gardens
     */
    @Override
    public List<Garden> getGardens(int userID) {
        ArrayList<Garden> gardenList = new ArrayList<>();
        for (Garden garden : gardens) {
            if (garden.userID() == userID) {
                gardenList.add(garden);
            }
        }
        return gardenList;
    }

    /**
     * Get a list of users
     *
     * @param userID the user ID
     * @return a list of users
     */
    @Override
    public List<User> getUsers(int userID) {
        ArrayList<User> userList = new ArrayList<>();
        for (Garden garden : gardens) {
            if (garden.userID() == userID) {
                userList.add(new User(garden.userID()));
            }
        }
        return userList;
    }
}
