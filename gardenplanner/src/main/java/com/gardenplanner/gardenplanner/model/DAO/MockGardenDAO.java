package com.gardenplanner.gardenplanner.model.DAO;

import com.gardenplanner.gardenplanner.model.Garden;

import java.util.ArrayList;

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
    public Garden[] getGardens(String query) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Garden[] getGardens(String query, int userid) {
        // TODO Auto-generated method stub
        return null;
    }
}
