package com.gardenplanner.gardenplanner.model.DAO;

import com.gardenplanner.gardenplanner.model.Plant;

import java.util.ArrayList;
import java.util.List;

public class MockPlantDAO implements IPlantDAO {
    private final ArrayList<Plant> plants = new ArrayList<Plant>();
    private int autoIncrementedId = 0;

    @Override
    public void insert(Plant plant) {
        plant.setID(autoIncrementedId);
        autoIncrementedId++;

        plants.add(plant);
    }

    @Override
    public void delete(Plant plant) {
        plants.remove(plant);
    }

    @Override
    public List<Plant> getPlants(int userID) {
        ArrayList<Plant> plantsList = new ArrayList<Plant>();
        for (Plant plant : plants) {
            if (plant.userID() == userID) {
                plantsList.add(plant);
            }
        }
        return plantsList;
    }
}
