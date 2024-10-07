package com.gardenplanner.gardenplanner.model.DAO;

import com.gardenplanner.gardenplanner.model.Garden;

import java.util.ArrayList;
import java.util.List;

public class MockGardenDAO implements IGardenDAO {
    private ArrayList<Garden> gardens = new ArrayList<>();
    private int autoIncrementedId = 0;

    @Override
    public void insert(Garden garden) {
        garden.setID(autoIncrementedId);
        autoIncrementedId++;

        gardens.add(garden);
    }

    @Override
    public void delete(Garden garden) {
        gardens.remove(garden);
    }

    @Override
    public List<Garden> getGardens(int userID) {
        ArrayList<Garden> gardenList = new ArrayList<>();
        for (int i = 0; i < gardens.size(); i++) {
            if (gardens.get(i).userID() == userID) {
                gardenList.add(gardens.get(i));
            }
        }
        return gardenList;
    }
}
