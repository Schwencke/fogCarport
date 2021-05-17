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
        System.out.println(result.add(new Material(1601, 6)));
        assertEquals(result, carportCalc.calcPost(6000, 7800));
    }

    @Test
    void calcBeam() throws UserException {
        List<Material> result = new ArrayList<>();
        System.out.println(result.add(new Material(1514, 2)));
        System.out.println(result.add(new Material(1506, 2)));
        assertEquals(result, carportCalc.calcBeam(6000, 7800));
    }

    @Test
    void calcRafter() throws UserException {
        List<Material> result = new ArrayList<>();
        System.out.println(result.add(new Material(1534, 15)));
        assertEquals(result, carportCalc.calcRafter(6000, 7800));
    }

    @Test
    void calcRoofing() throws UserException {
        List<Material> result = new ArrayList<>();
        result.add(new Material(2006, 6));
        result.add(new Material(2001, 6));

        assertEquals(result, carportCalc.calcRoofing(6000, 7800));
    }

    @Test
    void sternUnderFrontAndBack() {
        List<Integer> arrayList = new ArrayList<>();
        arrayList.add(4);
        arrayList.add(360);
        assertEquals(arrayList, carportCalc.calcStern(2, 600));
    }

    @Test
    void sternUnderSides() {
        List<Integer> arrayList = new ArrayList<>();
        arrayList.add(4);
        arrayList.add(540);
        assertEquals(arrayList, carportCalc.calcStern(2, 780));
    }

    @Test
    void sternOverFront() {
        List<Integer> arrayList = new ArrayList<>();
        arrayList.add(2);
        arrayList.add(360);
        assertEquals(arrayList, carportCalc.calcStern(1, 600));
    }

    @Test
    void sternOverSides() {
        List<Integer> arrayList = new ArrayList<>();
        arrayList.add(4);
        arrayList.add(540);
        assertEquals(arrayList, carportCalc.calcStern(2, 780));
    }

    @Test
    void sternWaterSides() {
        List<Integer> arrayList = new ArrayList<>();
        arrayList.add(4);
        arrayList.add(540);
        assertEquals(arrayList, carportCalc.calcStern(2, 780));
    }

    @Test
    void sternWaterFront() {
        List<Integer> arrayList = new ArrayList<>();
        arrayList.add(2);
        arrayList.add(360);
        assertEquals(arrayList, carportCalc.calcStern(2, 600));
    }
}