package com.gardenplanner.gardenplanner.model;

import com.gardenplanner.gardenplanner.model.DAO.IGardenDAO;

import java.util.List;

public class GardenManager {
    private IGardenDAO gardenDAO;
    public GardenManager(IGardenDAO gardenDao) {
        this.gardenDAO = gardenDao;
    }

    public void insert(Garden garden) {
        gardenDAO.insert(garden);
    }

    public List<Garden> searchGardens(int userID, String query) {
        return gardenDAO.getGardens(userID);
    }
}
