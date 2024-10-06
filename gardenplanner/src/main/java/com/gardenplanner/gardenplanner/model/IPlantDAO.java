package com.gardenplanner.gardenplanner.model;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface IPlantDAO {
    void createTable() throws SQLException;

    void insert(Plant plant) throws SQLException;

    void delete(Plant plant) throws SQLException;

    ResultSet getPlants(int userID) throws SQLException;
}
