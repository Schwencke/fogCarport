package business.services;

import business.exceptions.UserException;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CarportCalcTest {

    CarportCalc carportCalc = new CarportCalc();

    @Test
    void calcPost() throws UserException {
        List<Object> objectList = new ArrayList<>();
        objectList.add(1601);
        objectList.add(6);
        objectList.add(10.51);
        assertEquals(objectList, carportCalc.calcPost(600, 780));
    }

    @Test
    void calcBeam() {
        assertEquals(2, carportCalc.calcBeam(600));
    }

    @Test
    void calcRafter() {
        assertEquals(15, carportCalc.calcRafter(780));
    }

    @Test
    void calcRoofing() {
        List<Integer> arrayList = new ArrayList<>();
        arrayList.add(6);
        arrayList.add(600);
        arrayList.add(360);
        assertEquals(arrayList, carportCalc.calcRoofing(600, 780));
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