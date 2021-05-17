package business.services;

import business.entities.Material;
import business.exceptions.UserException;
import business.persistence.Database;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CarportCalcTest {

    private final static String USER = "root";
    private final static String PASSWORD = "1234";
    private final static String URL = "jdbc:mysql://localhost:3306/carport?serverTimezone=CET&useSSL=false";

    private static Database database;
    private static CarportCalc carportCalc;

    @BeforeAll
    public static void setUpClass() {
        try {
            database = new Database(USER, PASSWORD, URL);
            carportCalc = new CarportCalc(database);
        } catch (ClassNotFoundException e) {
            fail("Database connection failed. Missing jdbc driver");
        }
    }

    @Test
    void calcPost() throws UserException {
        List<Material> result = new ArrayList<>();
        System.out.println(result.add(new Material()));
        assertEquals(result, carportCalc.calcPost(6000, 7800));
    }

    @Test
    void calcBeam() throws UserException {
        List<Material> result = new ArrayList<>();
        System.out.println(result.add(new Material()));
        assertEquals(result, carportCalc.calcBeam(6000, 7800));
    }

    @Test
    void calcRafter() throws UserException {
        List<Material> result = new ArrayList<>();
        System.out.println(result.add(new Material()));
        assertEquals(result, carportCalc.calcRafter(6000, 7800));
    }

    @Test
    void calcRoofing() throws UserException {
        List<Material> result = new ArrayList<>();
        System.out.println(result.add(new Material()));
        assertEquals(result, carportCalc.calcRoofing(6000, 7800));
    }

    @Test
    void sternUnderFrontAndBack() throws UserException {
        List<Material> result = new ArrayList<>();
        System.out.println(result.add(new Material()));
        assertEquals(result, carportCalc.calcSternUnderFrontAndBack(6000));
    }
}