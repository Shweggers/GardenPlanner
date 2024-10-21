import com.gardenplanner.gardenplanner.model.DataStore;
import com.gardenplanner.gardenplanner.model.DatabaseConnection;
import com.gardenplanner.gardenplanner.model.User;
import com.gardenplanner.gardenplanner.model.UserManager;
import org.junit.jupiter.api.Test;

import javax.xml.crypto.Data;
import java.sql.Connection;

import static org.junit.jupiter.api.Assertions.*;

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