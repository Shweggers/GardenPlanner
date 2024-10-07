package com.gardenplanner.gardenplanner.model;

import com.gardenplanner.gardenplanner.model.DAO.IGardenDAO;

public class GardenManager {
    private IGardenDAO gardenDAO;
    public GardenManager(IGardenDAO gardenDao) {
        this.gardenDAO = gardenDao;
    }

    public void insert(Garden garden) {
        gardenDAO.insert(garden);
    }

    public Garden[] getGardens(String query) {
        // TODO Auto-generated method stub
        return null;
    }

    public Garden[] getGardens(String query, int userid) {
        // TODO Auto-generated method stub
        return null;
    }
}
