package business.persistence;

import business.entities.Material;
import business.exceptions.UserException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MaterialMapperTest {

    private final static String USER = "root";
    private final static String PASSWORD = "1234";
    private final static String URL = "jdbc:mysql://localhost:3306/carport?serverTimezone=CET&useSSL=false";

    private static Database database;
    private static MaterialMapper materialMapper;

    @BeforeAll
    public static void setUpClass() {
        try {
            database = new Database(USER, PASSWORD, URL);
            materialMapper = new MaterialMapper(database);
        } catch (ClassNotFoundException e) {
            fail("Database connection failed. Missing jdbc driver");
        }
    }

    @BeforeEach
    void setUp() {
    }

    @Test
    void getPost() throws UserException {
        Material material = materialMapper.getMaterialById(1601);
        assertEquals("97x97 mm. trykimp. stolpe", material.getName());
    }
}