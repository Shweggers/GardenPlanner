import com.gardenplanner.gardenplanner.model.DatabaseConnection;
import org.junit.jupiter.api.Test;

import java.sql.Connection;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class DatabaseTest {
    /**
     * Test the connection to the database
     */
    @Test
    public void testConnection() {
        Connection conn = DatabaseConnection.getInstance();
        assertNotNull(conn);
    }
}