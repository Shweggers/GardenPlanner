package com.gardenplanner.gardenplanner;

import com.gardenplanner.gardenplanner.model.*;

import com.gardenplanner.gardenplanner.model.DAO.MockFriendDAO;
import org.junit.jupiter.api.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class FriendManagerTest {
    private FriendManager friendManager;
    private Friend[] friends = {
            new Friend(1, 2, "Jane"),
            new Friend(1, 3, "JaneSmith"),
            new Friend(1, 4, "Smith"),
            new Friend(1, 5, "Smithers"),
            new Friend(1, 6, "Williams"),
            new Friend(1, 7, "Will"),
            new Friend(2, 1, "John"),
            new Friend(2, 3, "JaneSmith"),
            new Friend(2, 4, "Smith"),
            new Friend(2, 5, "Smithers"),
            new Friend(3, 2, "Jane"),
            new Friend(3, 4, "Smith"),
            new Friend(4, 2, "Jane"),
            new Friend(4, 3, "JaneSmith"),
            new Friend(5, 1, "John"),
    };

    @BeforeEach
    public void setUp() {
        friendManager = new FriendManager(new MockFriendDAO());
    }

    @Test
    public void testSearchFriendsInOneFriend() {
        friendManager.insert(friends[0]);

        List<Friend> friendslist = friendManager.searchFriends(1, "Jane");
        assertEquals(1, friendslist.size());
        for (Friend friend : friendslist) {
            assertEquals("Jane", friend.friendName());
        }
    }

    @Test
    public void testSearchFriendsInMultipleFriends() {
        for (Friend friend : friends) {
            friendManager.insert(friend);
        }

        List<Friend> friendslist = friendManager.searchFriends(1, "Smithers");
        assertEquals(1, friendslist.size());
        for (Friend friend : friendslist) {
            assertEquals("Smithers", friend.friendName());
        }
    }

    @Test
    public void testSearchFriendsNoResults() {
        for (Friend friend : friends) {
            friendManager.insert(friend);
        }

        List<Friend> friendslist = friendManager.searchFriends(1, "Dan");
        assertEquals(0, friendslist.size());
    }

    @Test
    public void testSearchFriendsEmptyQuery() {
        for (Friend friend : friends) {
            friendManager.insert(friend);
        }

        List<Friend> friendslist = friendManager.searchFriends(1, "");
        assertEquals(6, friendslist.size());
    }

    @Test
    public void testSearchFriendsNullQuery() {
        for (Friend friend : friends) {
            friendManager.insert(friend);
        }

        List<Friend> friendslist = friendManager.searchFriends(1 ,null);
        assertEquals(6, friendslist.size());
    }

    @Test
    public void testSearchFriendsCaseInsensitive() {
        for (Friend friend : friends) {
            friendManager.insert(friend);
        }

        List<Friend> friendslist = friendManager.searchFriends(1, "will");
        assertEquals(2, friendslist.size());
        for (Friend friend : friendslist) {
            assertTrue(friend.friendName().equals("Will") || friend.friendName().equals("Williams"));
        }
    }

    @Test
    public void testSearchFriendsPartialQuery() {
        for (Friend friend : friends) {
            friendManager.insert(friend);
        }

        List<Friend> friendslist = friendManager.searchFriends(1, "ith");
        assertEquals(3, friendslist.size());
        for (Friend friend : friendslist) {
            assertTrue(friend.friendName().equals("JaneSmith") || friend.friendName().equals("Smith") || friend.friendName().equals("Smithers"));
        }
    }

    @Test
    public void testSearchFriendsEmptyFriends() {
        List<Friend> friendslist = friendManager.searchFriends(1, "Smith");
        assertEquals(0, friendslist.size());
    }

    @Test
    public void testAreFriendsOneFriend() {
        friendManager.insert(friends[0]);

        assertTrue(friendManager.areFriends(1, "Jane"));
    }

    @Test
    public void testAreFriendsMultipleFriends() {
        for (Friend friend : friends) {
            friendManager.insert(friend);
        }

        assertFalse(friendManager.areFriends(4, "Smithers"));
    }

    @Test
    public void testAreFriendsNoResults() {
        for (Friend friend : friends) {
            friendManager.insert(friend);
        }

        assertFalse(friendManager.areFriends(4, "Smithers"));
    }

    @Test
    public void testAreFriendsEmptyQuery() {
        for (Friend friend : friends) {
            friendManager.insert(friend);
        }

        assertFalse(friendManager.areFriends(1, ""));
    }

    @Test
    public void testAreFriendsNullQuery() {
        for (Friend friend : friends) {
            friendManager.insert(friend);
        }

        assertFalse(friendManager.areFriends(1, null));
    }

    @Test
    public void testAreFriendsCaseInsensitive() {
        for (Friend friend : friends) {
            friendManager.insert(friend);
        }

        assertFalse(friendManager.areFriends(2, "smith"));
    }

    @Test
    public void testAreFriendsPartialQuery() {
        for (Friend friend : friends) {
            friendManager.insert(friend);
        }

        assertFalse(friendManager.areFriends(2, "ith"));
    }

    @Test
    public void testAreFriendsEmptyFriends() {
        assertFalse(friendManager.areFriends(3, "Smith"));
    }
}
