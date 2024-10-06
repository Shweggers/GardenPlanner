package com.gardenplanner.gardenplanner.model;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface IGardenDAO {

    void insert(Garden garden) throws SQLException;

    void delete(Garden garden) throws SQLException;

    ResultSet getGardens(int userID) throws SQLException;
}
