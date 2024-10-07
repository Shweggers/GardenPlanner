package com.gardenplanner.gardenplanner.model.DAO;

import com.gardenplanner.gardenplanner.model.Plant;

import java.util.List;

public interface IPlantDAO {

    void insert(Plant plant);

    void delete(Plant plant);

    List<Plant> getPlants(int userID);
}
