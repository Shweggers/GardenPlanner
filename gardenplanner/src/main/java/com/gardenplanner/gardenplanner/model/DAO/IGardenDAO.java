package com.gardenplanner.gardenplanner.model.DAO;

import com.gardenplanner.gardenplanner.model.Garden;

import java.util.List;

public interface IGardenDAO {

    void insert(Garden garden);

    void delete(Garden garden);

    List<Garden> getGardens(int userid);
}
