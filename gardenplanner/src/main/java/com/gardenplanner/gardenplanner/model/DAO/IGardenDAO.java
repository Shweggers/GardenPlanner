package com.gardenplanner.gardenplanner.model.DAO;

import com.gardenplanner.gardenplanner.model.Garden;

public interface IGardenDAO {

    void insert(Garden garden);

    void delete(Garden garden);

    Garden[] getGardens(String query);

    Garden[] getGardens(String query, int userid);
}
