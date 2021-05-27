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
        Material material = new Material();
        List<Material> result = new ArrayList<>();
        result.add(carportCalc.newItem(6, 1601, material));
        assertEquals(result, carportCalc.calcPost(6000, 7800));
    }

    @Test
    void calcBeam() throws UserException {
        Material material = new Material();
        List<Material> result = new ArrayList<>();
        result.add(carportCalc.newItem(2, 1514, material));
        result.add(carportCalc.newItem(2, 1506, material));
        assertEquals(result, carportCalc.calcBeam(6000, 7800));
    }

    @Test
    void calcRafter() throws UserException {
        Material material = new Material();
        List<Material> result = new ArrayList<>();
        result.add(carportCalc.newItem(15, 1534, material));
        assertEquals(result, carportCalc.calcRafter(6000, 7800));
    }

    @Test
    void calcRoofing() throws UserException {
        assertEquals(new Material(), carportCalc.calcRoofing(6000, 7800));
    }

    @Test
    void sternUnderFrontAndBack() throws UserException {
        assertEquals(new Material(), carportCalc.calcSternUnderFrontAndBack(6000));
    }

    @Test
    void newItem() {
    }

    @Test
    void calcStern() {
    }

    @Test
    void calcSternUnderFrontAndBack() {
    }

    @Test
    void calcSternUnderSides() {
    }

    @Test
    void calcSternOverFront() {
    }

    @Test
    void calcSternOverSides() {
    }

    @Test
    void calcSternWaterFront() {
    }

    @Test
    void calcSternWaterSides() {
    }

    @Test
    void testCalcRoofing() {
    }
}