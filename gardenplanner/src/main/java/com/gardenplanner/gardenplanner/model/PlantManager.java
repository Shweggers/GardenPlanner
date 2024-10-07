package com.gardenplanner.gardenplanner.model;

import com.gardenplanner.gardenplanner.model.Plant;
import com.gardenplanner.gardenplanner.model.DAO.IPlantDAO;

import java.util.List;

public class PlantManager {
    private IPlantDAO plantDAO;
    public PlantManager(IPlantDAO plantDAO) {
        this.plantDAO = plantDAO;
    }

    public void insert(Plant plant) {
        plantDAO.insert(plant);
    }

    public void delete(Plant plant) {
        plantDAO.delete(plant);
    }

    public List<Plant> searchPlants(int userID, String query) {
        return plantDAO.getPlants(userID)
                .stream()
                .filter(plant -> isPlantMatched(plant, query))
                .toList();
    }

    private boolean isPlantMatched(Plant plant, String query) {
        query = query == null ? "" : query.toLowerCase();
        return query.isEmpty() || plant.plantID().toLowerCase().contains(query);
    }
}
