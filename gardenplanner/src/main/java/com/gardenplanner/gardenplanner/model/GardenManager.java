package com.gardenplanner.gardenplanner.model;

import com.gardenplanner.gardenplanner.model.DAO.IGardenDAO;

import java.util.List;

public class GardenManager {
    private final IGardenDAO gardenDAO;
    public GardenManager(IGardenDAO gardenDao) {
        this.gardenDAO = gardenDao;
    }

    public void insert(Garden garden) {
        gardenDAO.insert(garden);
    }

    public void delete(Garden garden) {
        gardenDAO.delete(garden);
    }

    public List<Garden> searchGardens(int userID, String query) {
        return gardenDAO.getGardens(userID)
                .stream()
                .filter(garden -> isGardenMatched(garden, query))
                .toList();
    }

    private boolean isGardenMatched(Garden garden, String query) {
        query = query == null ? "" : query.toLowerCase();
        return query.isEmpty() || garden.name().toLowerCase().contains(query);
    }
}
