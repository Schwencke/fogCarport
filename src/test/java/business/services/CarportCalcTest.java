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
        List<Object> result = new ArrayList<>();
        result.add(1601);
        result.add(6);
        result.add(112.5);
        assertEquals(result, carportCalc.calcPost(6000, 7800));
    }

    @Test
    void calcBeam() throws UserException {
        List<Object> result = new ArrayList<>();
        result.add(1);
        assertEquals(result, carportCalc.calcBeam(6000, 7800));
    }

    @Test
    void calcRafter() throws UserException {
        List<Object> result = new ArrayList<>();
        result.add(1);
        assertEquals(result, carportCalc.calcRafter(6000, 7800));
    }

    @Test
    void calcRoofing() {
        List<Integer> arrayList = new ArrayList<>();
        arrayList.add(1);
        assertEquals(arrayList, carportCalc.calcRoofing(6000, 7800));
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