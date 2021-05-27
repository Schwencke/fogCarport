package business.persistence;

import business.entities.User;
import business.exceptions.UserException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.sql.Statement;

import static org.junit.jupiter.api.Assertions.*;

public class UserMapperTest {

    private final static String USER = "root";
    private final static String PASSWORD = "1234";
    private final static String URL = "jdbc:mysql://localhost:3306/carport_test?serverTimezone=CET&useSSL=false";

    private static Database database;
    private static UserMapper userMapper;

    @BeforeAll
    public static void setUpClass() {
        try {
            database = new Database(USER, PASSWORD, URL);
            userMapper = new UserMapper(database);
        } catch (ClassNotFoundException e) {   // kan ikke finde driveren i database klassen
            fail("Database connection failed. Missing jdbc driver");
        }
    }

    @BeforeEach
    public void setUp() {

        // reset test database
        try (Statement stmt = database.connect().createStatement()) {
            stmt.execute("drop table if exists user");
            stmt.execute("create table user LIKE carport.user;");
            stmt.execute(
                    "insert into `user` values " +
                            "(3,1,'Jens','Jensengade 1',3700,'10203040','jens@somewhere.com','jensen'), " +
                            "(4,1,'Ken','Kensengade 1',3720,'10203040','ken@somewhere.com','kensen'), " +
                            "(5,1,'Robin','Jokergaden 1',3750,'10203040','robin@somewhere.com','batman')");
        } catch (SQLException ex) {
            System.out.println("Could not open connection to database: " + ex.getMessage());
        }
    }

    @Test
    public void testSetUpOK() {
        // Just check that we have a connection.
        assertNotNull(database);
    }

    @Test
    public void testLogin01() throws UserException, SQLException {
        // Can we log in
        User user = userMapper.login("jens@somewhere.com", "jensen");
        assertTrue(user != null);
    }

//    @Test
//    public void testLogin02() throws UserException {
//        // We should get an exception if we use the wrong password
//        assertThrows(UserException.class, () ->
//            {User user = userMapper.login( "jens@somewhere.com", "jensen" ); });
//
//    }

    @Test
    public void testLogin03() throws UserException, SQLException {
        // Jens is supposed to be a customer
        User user = userMapper.login("jens@somewhere.com", "jensen");
        assertEquals(1, user.getRoleId());
    }

    @Test
    public void testCreateUser01() throws UserException, SQLException {
        // Can we create a new user - Notice, if login fails, this will fail
        // but so would login01, so this is OK
        User original = new User("KingKong", "NA", 3700, "10203040", "king@kong.com", "uhahvorhemmeligt");
        userMapper.createUser(original);
        User retrieved = userMapper.login("king@kong.com", "uhahvorhemmeligt");
        assertEquals("KingKong", retrieved.getName());
    }

}
