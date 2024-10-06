package com.gardenplanner.gardenplanner;

import com.gardenplanner.gardenplanner.model.*;

import org.junit.jupiter.api.*;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;


public class FriendManagerTest {
    private FriendManager friendManager;
    private Friend[] friends = {
        new Friend("John", "Jane"),
        new Friend("John", "Smith"),
        new Friend("John", "Johnson"),
        new Friend("Smith", "Williams"),
        new Friend("Smith", "Brown"),
        new Friend("Smith", "Johnson")
    };

    @BeforeEach
    public void setUp() {
        friendManager = new FriendManager(new MockFriendDAO());
    }

    @Test
    public void testSearchInOneFriend() {
        friendManager.insert(friends[0].friend1(), friends[0].friend2());
        assertEquals(1, friendManager.getFriends(friends[0].friend1()).length);
        assertEquals(1, friendManager.getFriends(friends[1].friend1()).length);
        assertEquals(friendManager.getFriends(friends[0].friend1()).length, friendManager.getFriends(friends[1].friend1()).length);
    }

    @Test
    public void testSearchInMultipleFriends() {
        for (Friend friend : friends) {
            friendManager.insert(friend.friend1(), friend.friend2());
        }
        assertEquals(5, friendManager.getFriends(friends[0].friend1()).length);
        assertEquals(1, friendManager.getFriends(friends[0].friend2()).length);
        assertEquals(4, friendManager.getFriends(friends[1].friend2()).length);
        assertEquals(2, friendManager.getFriends(friends[2].friend2()).length);
        assertEquals(1, friendManager.getFriends(friends[4].friend2()).length);
    }

    @Test
    public void testSearchNoResults() {
        for (Friend friend : friends) {
            friendManager.insert(friend.friend1(), friend.friend2());
        }
        assertEquals(0, friendManager.getFriends("Dan").length);
    }

    @Test
    public void testSearchEmptyQuery() {
        for (Friend friend : friends) {
            friendManager.insert(friend.friend1(), friend.friend2());
        }
        assertEquals(12, friendManager.getFriends("").length);
    }

    @Test
    public void testSearchNullQuery() {
        for (Friend friend : friends) {
            friendManager.insert(friend.friend1(), friend.friend2());
        }
        assertEquals(12, friendManager.getFriends(null).length);
    }

    @Test
    public void testSearchCaseInsensitive() {
        for (Friend friend : friends) {
            friendManager.insert(friend.friend1(), friend.friend2());
        }
        assertEquals(1, friendManager.getFriends("jane").length);
    }

    @Test
    public void testSearchPartialQuery() {
        for (Friend friend : friends) {
            friendManager.insert(friend.friend1(), friend.friend2());
        }
        assertEquals(5, friendManager.getFriends("Jo").length);
    }

    @Test
    public void testSearchEmptyFriends() {
        assertEquals(0, friendManager.getFriends("John").length);
    }

    @Test
    public void testAreFriends() {
        for (Friend friend : friends) {
            friendManager.insert(friend.friend1(), friend.friend2());
        }
        assertTrue(friendManager.areFriends(friends[0].friend1(), friends[0].friend2()));
        assertTrue(friendManager.areFriends(friends[1].friend1(), friends[1].friend2()));
        assertTrue(friendManager.areFriends(friends[2].friend1(), friends[2].friend2()));
        assertTrue(friendManager.areFriends(friends[3].friend1(), friends[3].friend2()));
        assertTrue(friendManager.areFriends(friends[4].friend1(), friends[4].friend2()));
        assertTrue(friendManager.areFriends(friends[5].friend1(), friends[5].friend2()));
    }
}
