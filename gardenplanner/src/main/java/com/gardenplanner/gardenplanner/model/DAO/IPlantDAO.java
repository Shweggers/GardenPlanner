package com.gardenplanner.gardenplanner.model.DAO;

import com.gardenplanner.gardenplanner.model.Plant;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface IPlantDAO {

    void insert(Plant plant) throws SQLException;

    void delete(Plant plant) throws SQLException;

    ResultSet getPlants(int userID) throws SQLException;
}
