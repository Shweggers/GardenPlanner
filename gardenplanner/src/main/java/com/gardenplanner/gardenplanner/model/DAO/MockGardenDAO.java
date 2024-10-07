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
        // TODO Auto-generated method stub

    }

    @Override
    public List<Garden> getGardens(int userID) {
        // TODO Auto-generated method stub
        return null;
    }
}
