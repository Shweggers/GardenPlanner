import com.gardenplanner.gardenplanner.model.DAO.MockFriendDAO;
import com.gardenplanner.gardenplanner.model.Friend;
import com.gardenplanner.gardenplanner.model.FriendManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests for the FriendManager class
 */
public class FriendManagerTest {
    private final Friend[] friends = {
            new Friend(1, "Jane"),
            new Friend(1, "JaneSmith"),
            new Friend(1, "Smith"),
            new Friend(1, "Smithers"),
            new Friend(1, "Williams"),
            new Friend(1, "Will"),
            new Friend(2, "John"),
            new Friend(2, "JaneSmith"),
            new Friend(2, "Smith"),
            new Friend(2, "Smithers"),
            new Friend(3, "Jane"),
            new Friend(3, "Smith"),
            new Friend(4, "Jane"),
            new Friend(4, "JaneSmith"),
            new Friend(5, "John"),
    };
    private FriendManager friendManager;

    /**
     * Set up the test friend manager
     */
    @BeforeEach
    public void setUp() {
        friendManager = new FriendManager(new MockFriendDAO());
    }

    /**
     * Test searching for friends in a list with one friend
     */
    @Test
    public void testSearchFriendsInOneFriend() {
        friendManager.insert(friends[0]);

        List<Friend> friendslist = friendManager.searchFriends(1, "Jane");
        assertEquals(1, friendslist.size());
        for (Friend friend : friendslist) {
            assertEquals("Jane", friend.friendName());
        }
    }

    /**
     * Test searching for friends in a list with multiple friends
     */
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

    /**
     * Test searching for friends in a list with no results
     */
    @Test
    public void testSearchFriendsNoResults() {
        for (Friend friend : friends) {
            friendManager.insert(friend);
        }

        List<Friend> friendslist = friendManager.searchFriends(1, "Dan");
        assertEquals(0, friendslist.size());
    }

    /**
     * Test searching for friends with an empty query
     */
    @Test
    public void testSearchFriendsEmptyQuery() {
        for (Friend friend : friends) {
            friendManager.insert(friend);
        }

        List<Friend> friendslist = friendManager.searchFriends(1, "");
        assertEquals(6, friendslist.size());
    }

    /**
     * Test searching for friends with a null query
     */
    @Test
    public void testSearchFriendsNullQuery() {
        for (Friend friend : friends) {
            friendManager.insert(friend);
        }

        List<Friend> friendslist = friendManager.searchFriends(1, null);
        assertEquals(6, friendslist.size());
    }

    /**
     * Test searching for friends with a case-insensitive query
     */
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

    /**
     * Test searching for friends with a partial query
     */
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

    /**
     * Test searching for friends in an empty list
     */
    @Test
    public void testSearchFriendsEmptyFriends() {
        List<Friend> friendslist = friendManager.searchFriends(1, "Smith");
        assertEquals(0, friendslist.size());
    }

    /**
     * Test checking a user has friended another user with one friend in the list
     */
    @Test
    public void testAreFriendsOneFriend() {
        friendManager.insert(friends[0]);

        assertTrue(friendManager.areFriends(1, "Jane"));
    }

    /**
     * Test checking a user has friended another user with multiple friends in the list
     */
    @Test
    public void testAreFriendsMultipleFriends() {
        for (Friend friend : friends) {
            friendManager.insert(friend);
        }

        assertFalse(friendManager.areFriends(4, "Smithers"));
    }

    /**
     * Test checking a user has not friended another user with no results
     */
    @Test
    public void testAreFriendsNoResults() {
        for (Friend friend : friends) {
            friendManager.insert(friend);
        }

        assertFalse(friendManager.areFriends(4, "Smithers"));
    }

    /**
     * Test checking a user has not friended another user with an empty query
     */
    @Test
    public void testAreFriendsEmptyQuery() {
        for (Friend friend : friends) {
            friendManager.insert(friend);
        }

        assertFalse(friendManager.areFriends(1, ""));
    }

    /**
     * Test checking a user has not friended another user with a null query
     */
    @Test
    public void testAreFriendsNullQuery() {
        for (Friend friend : friends) {
            friendManager.insert(friend);
        }

        assertFalse(friendManager.areFriends(1, null));
    }

    /**
     * Test checking a user has not friended another user with a case-insensitive query
     */
    @Test
    public void testAreFriendsCaseInsensitive() {
        for (Friend friend : friends) {
            friendManager.insert(friend);
        }

        assertFalse(friendManager.areFriends(2, "smith"));
    }

    /**
     * Test checking a user has not friended another user with a partial query
     */
    @Test
    public void testAreFriendsPartialQuery() {
        for (Friend friend : friends) {
            friendManager.insert(friend);
        }

        assertFalse(friendManager.areFriends(2, "ith"));
    }

    /**
     * Test checking a user has not friended another user with an empty list
     */
    @Test
    public void testAreFriendsEmptyFriends() {
        assertFalse(friendManager.areFriends(3, "Smith"));
    }

    /**
     * Test deleting a friend
     */
    @Test
    public void testDeleteFriends() {
        for (Friend friend : friends) {
            friendManager.insert(friend);
        }

        friendManager.delete(friends[7]);
        List<Friend> friendslist = friendManager.searchFriends(2, "JaneSmith");
        assertEquals(0, friendslist.size());
    }

    /**
     * Test converting friend object toString
     */
    @Test
    public void testFriendToString() {
        Friend friend = new Friend(1, "Jane");
        assertEquals("Friend{userID=1, friendName='Jane'}", friend.toString());
    }
}
