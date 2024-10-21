import com.gardenplanner.gardenplanner.model.DataStore;
import com.gardenplanner.gardenplanner.model.DatabaseConnection;
import com.gardenplanner.gardenplanner.model.User;
import org.junit.jupiter.api.Test;

import java.sql.Connection;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class DatabaseTest {
    DataStore instance = null;

    /**
     * Test the connection to the database
     */
    @Test
    public void testConnection() {
        Connection conn = DatabaseConnection.getInstance();
        assertNotNull(conn);
    }

    /**
     * Test getting Data instance that is not null
     */
    @Test
    public void testGetInstanceNotNull() {
        instance = DataStore.getInstance();
        assertNotNull(instance);
    }

    /**
     * Test getting and setting current user
     */
    @Test
    public void testSetGetCurrentUser() {
        instance = DataStore.getInstance();
        User testUser = new User(1);
        instance.setCurrentUser(testUser);
        assertEquals(new User(1), instance.getCurrentUser());
    }
}