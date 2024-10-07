package com.gardenplanner.gardenplanner.model.DAO;

import com.gardenplanner.gardenplanner.model.User;

public class MockUserDAO implements IUserDAO {

    @Override
    public void insert(User user) {
        // TODO Auto-generated method stub

    }

    @Override
    public User getUser(String username) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void updatePassword(String username, String password) {
        // TODO Auto-generated method stub
    }

    @Override
    public int returnID(String username) {
        return 0;
    }
}
